package com.mindhub.Homebanking.controllers;
import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.models.CardColor;
import com.mindhub.Homebanking.models.CardType;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.repositories.CardRepository;
import com.mindhub.Homebanking.services.CardService;
import com.mindhub.Homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import static com.mindhub.Homebanking.Util.RandomNumber.getRandomNumber;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientService clientService;

    @RequestMapping("/cards")
    public Set<CardDTO> getCards(){
        return cardService.getCards();
    }

    @PatchMapping("/cards/{id}")
    public ResponseEntity<Object> deleteCard (@PathVariable Long id, Authentication authentication){
        Client client = clientService.getClientByEmail(authentication.getName());
        Card card = cardService.getCard(id);

        if(card.isActiveCard()){
            card.setActiveCard(false);
        }

        cardService.saveCard(card);

        return new ResponseEntity <>("Card Eliminated", HttpStatus.ACCEPTED);

    }

    @PatchMapping("/clients/current/cards")
    public void expiredCard(Authentication authentication){
        Client client = clientService.getClientByEmail(authentication.getName());
        Set<Card> cards = client.getCard();

        cards.stream().forEach(card -> {

            if (LocalDateTime.now().isAfter(card.getThruDate())) {
                card.setCardExpired(true);
            }
             cardService.saveCard(card);
        });
    }

    @RequestMapping (value = "/clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> createCard(Authentication authentication, @RequestParam CardType cardType, @RequestParam CardColor cardColor){
        Client client = clientService.getClientByEmail(authentication.getName());

        Set<Card> cards = client.getCard();
        Set<Card> cardsCredit = cards.stream().filter(card -> card.getType() == CardType.CREDIT).collect(Collectors.toSet());
        Set<Card> cardsDebit = cards.stream().filter(card -> card.getType() == CardType.DEBIT).collect(Collectors.toSet());

        if(cardsDebit.size() >= 3 && cardType == CardType.DEBIT){
            return new ResponseEntity<>("403 Forbidden", HttpStatus.FORBIDDEN);
        }
        if(cardsCredit.size() >= 3 && cardType == CardType.CREDIT){
            return new ResponseEntity<>("403 Forbidden", HttpStatus.FORBIDDEN);
        }
        Card card = new Card(false,true, client.getName() + " " + client.getApellido(),cardType,cardColor,getRandomNumber(1000,9999),getRandomNumber(1000,9999),getRandomNumber(1000,9999),getRandomNumber(1000,9999),getRandomNumber(100,999), LocalDateTime.now(),LocalDateTime.now().plusYears(5),client);
        cardService.saveCard(card);
        return new ResponseEntity<>("Card Created!",HttpStatus.CREATED);
    }
}
