package com.mindhub.Homebanking.controllers;
import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.models.AccountType;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.services.AccountService;
import com.mindhub.Homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import static com.mindhub.Homebanking.Util.RandomNumber.getRandomNumber;



@RestController
@RequestMapping("/api")
public class AccountController {



    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientService clientService;

    @RequestMapping("/accounts")
    public Set<AccountDTO> getAccounts(){
        return accountService.getAccountsDTO();
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accountService.getAccountDTO(id);
    }

    @RequestMapping("/clients/current/accounts")

    public Set<AccountDTO> getAccount(Authentication authentication){
        Client client = clientService.getClientCurrent(authentication);
        return client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
    }

    @RequestMapping(value = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(Authentication authentication){

        Client client = clientService.getClientCurrent(authentication);
        Random random = new Random();

        if(client.getAccounts().size() >= 3){
            return new ResponseEntity<>("403 Forbidden",HttpStatus.FORBIDDEN);
        }

        Account account = new Account(true,"VIN" + getRandomNumber(10000000,99999999), LocalDateTime.now(),0,client, AccountType.CHECKING);
         accountService.saveAccount(account);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id, Authentication authentication){
        Client client = clientService.getClientCurrent(authentication);
        Account account = accountRepository.findById(id).orElse(null);


        assert account != null;
        if(account.isActiveAccount()){
            account.setActiveAccount(false);
        }



        accountService.saveAccount(account);

         return new ResponseEntity<>("borrado",HttpStatus.ACCEPTED);

    }

}
