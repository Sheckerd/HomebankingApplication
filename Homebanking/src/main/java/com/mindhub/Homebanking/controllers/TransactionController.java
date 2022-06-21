package com.mindhub.Homebanking.controllers;
import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.models.Transaction;
import com.mindhub.Homebanking.models.TransactionType;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.services.AccountService;
import com.mindhub.Homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api")
public class TransactionController {



    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientRepository clientrepository;

    @Autowired
    private AccountRepository accountRepository;




    @RequestMapping(value="/transactions/{id}", method = RequestMethod.GET)
    public TransactionDTO getTransactions(@PathVariable long id){

        return transactionService.getTransactions(id);
    }



    //@Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> createTransaction(Authentication authentication,
                                                    @RequestParam double amount,
                                                    @RequestParam String description,
                                                    @RequestParam String originAccountNumber,
                                                    @RequestParam String destinyAccountNumber) {

        Client client = clientrepository.findByEmail(authentication.getName());

     //   Account originAccount = accountService.findByNumber(originAccountNumber);
      //  Account destinyAccount = accountService.findByNumber(destinyAccountNumber);

        Account originAccount = accountRepository.findByNumber(originAccountNumber);
        Account destinyAccount = accountRepository.findByNumber(destinyAccountNumber);




        if (amount == 0 || description.isEmpty() || originAccountNumber.isEmpty() || destinyAccountNumber.isEmpty()) {
            return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }
        if (originAccountNumber == destinyAccountNumber) {
            return new ResponseEntity<>("Same Account Number", HttpStatus.FORBIDDEN);
        }
        if (originAccount == null) {
            return new ResponseEntity<>("Origin Account doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (!client.getAccounts().contains(originAccount)) {
            return new ResponseEntity<>("Origin Account does not belong to this client", HttpStatus.FORBIDDEN);
        }
        if (destinyAccount == null) {
            return new ResponseEntity<>("Destiny Account doesn't exist", HttpStatus.FORBIDDEN);

        }
        if (originAccount.getBalance() < amount) {
            return new ResponseEntity<>("Insufficient funds", HttpStatus.FORBIDDEN);
        }
        if (amount <= 0) {
            return new ResponseEntity<>("Invalid Transaction", HttpStatus.FORBIDDEN);
        }

        Transaction debit = new Transaction(TransactionType.DEBIT, amount, description, LocalDateTime.now(),originAccount,originAccount.getBalance() - amount);

        Transaction credit = new Transaction(TransactionType.CREDIT, amount, description, LocalDateTime.now(),destinyAccount, originAccount.getBalance() + amount);

        originAccount.setBalance(originAccount.getBalance() - amount);
        destinyAccount.setBalance(destinyAccount.getBalance() + amount);

        transactionService.saveTransaction(debit);
        transactionService.saveTransaction(credit);
        accountService.saveAccount(originAccount);
        accountService.saveAccount(destinyAccount);

        return new ResponseEntity<>("Transaction Complete!", HttpStatus.CREATED);
    }

}
