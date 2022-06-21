package com.mindhub.Homebanking.controllers;
import com.mindhub.Homebanking.dtos.LoanApplicationDTO;
import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RestController
@RequestMapping ("/api")
public class LoanController {




    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientLoanService clientLoanService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private LoanService loanService;

    @RequestMapping("/loans")
    public Set<LoanDTO> getLoans() {
        return loanService.getLoansDTO();
    }

@Transactional
@RequestMapping(value = "/loans",method = RequestMethod.POST)
    public ResponseEntity<Object> addLoan(Authentication authentication, @RequestBody LoanApplicationDTO loanApplicationDTO){

    Client client = clientService.getClientByEmail(authentication.getName());
    Account targetAccount = accountService.findByNumber(loanApplicationDTO.getDestinationAccount());
    Loan loanTypes = loanService.getLoanById(loanApplicationDTO.getidLoan());


    if(loanApplicationDTO.getidLoan() == 1 ){
        loanApplicationDTO.setPercentage(1.50);
    }
    if(loanApplicationDTO.getidLoan() == 2){
        loanApplicationDTO.setPercentage(1.15);
    }
    if(loanApplicationDTO.getidLoan() == 3){
        loanApplicationDTO.setPercentage(1.35);
    }


    if((client.getEmail() == "admin@admin.com" && loanApplicationDTO.getidLoan() == 4)){
        loanTypes.setMaxAmount(2000000);

    }
    if(loanApplicationDTO.getAmount() <= 0){
        return new ResponseEntity<>("Invalid amount", HttpStatus.FORBIDDEN);
    }
    if(loanApplicationDTO.getPayments() <= 0){
        return new ResponseEntity<>("Invalid payment quantity", HttpStatus.FORBIDDEN);
    }
    if(loanTypes == null){
        return new ResponseEntity<>("el prestamo no existe", HttpStatus.FORBIDDEN);
    }
    if(loanTypes.getMaxAmount() < loanApplicationDTO.getAmount()){
        return new ResponseEntity<>("es mucha plata", HttpStatus.FORBIDDEN);
    }

    if(!loanTypes.getPayments().contains(loanApplicationDTO.getPayments())){
        return new ResponseEntity<>("cambia la cuota papi", HttpStatus.FORBIDDEN);
    }
    if(targetAccount == null) {
        return new ResponseEntity<>("Account does not exist", HttpStatus.FORBIDDEN);
    }
    if(!client.getAccounts().contains(targetAccount)){
        return new ResponseEntity<>("algo", HttpStatus.FORBIDDEN);
    }


    ClientLoan  newClientLoan = new ClientLoan(loanApplicationDTO.getAmount() * loanApplicationDTO.getPercentage(), loanApplicationDTO.getPayments(),client,loanTypes);
    clientLoanService.saveClientLoan(newClientLoan);

    Transaction loanCredit = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loanApplicationDTO.getName() + " Credit Successful", LocalDateTime.now(),targetAccount, targetAccount.getBalance() + loanApplicationDTO.getAmount());
    transactionService.saveTransaction(loanCredit);

    targetAccount.setBalance(targetAccount.getBalance() + loanApplicationDTO.getAmount());
    accountService.saveAccount(targetAccount);



   return new ResponseEntity<>("Juegue caballero", HttpStatus.CREATED);

}

}
