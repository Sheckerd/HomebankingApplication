package com.mindhub.Homebanking.models;

import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")



    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account.id")
    private Account account;

    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime date;

    private double balance;

    public Transaction(){}

    public Transaction(TransactionType type, double amount, String description, LocalDateTime date, Account account,double balance){
        this.balance = balance;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.account = account;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getId() {return id;}

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
