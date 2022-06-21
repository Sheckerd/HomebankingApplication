package com.mindhub.Homebanking.services.implement;
import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.repositories.CardRepository;
import com.mindhub.Homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public Set<CardDTO> getCards() {
        return cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    @Override
    public Card getCard(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }
}
