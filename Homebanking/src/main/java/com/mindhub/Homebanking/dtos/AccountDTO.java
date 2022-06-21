package com.mindhub.Homebanking.dtos;
import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.models.AccountType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private long id;
    private String number;
    private LocalDateTime creationDate;
    private double balance;
    private AccountType accountType;
    private boolean activeAccount;
    private Set<TransactionDTO> transactions = new HashSet<>();




    public AccountDTO(){};

    public AccountDTO(Account account){
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getcreationDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
        this.accountType = account.getAccountType();
        this.activeAccount = account.isActiveAccount();
    }

    public long getId() {return id;}

    public boolean isActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(boolean activeAccount) {
        this.activeAccount = activeAccount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getNumber() {return number;}

    public LocalDateTime getCreationDate() {return creationDate;}

    public double getBalance() {return balance;}

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
}
