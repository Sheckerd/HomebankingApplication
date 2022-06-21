package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.Transaction;
import com.mindhub.Homebanking.models.TransactionType;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class TransactionDTO {

    private long id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime date;
    private double balance;

    public TransactionDTO(){}

    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.balance = transaction.getBalance();
    }

    public long getId() {
        return id;
    }
    public TransactionType getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public double getBalance() {
        return balance;
    }
}
