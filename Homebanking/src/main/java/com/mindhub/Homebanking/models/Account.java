package com.mindhub.Homebanking.models;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")




    private long id;

    private boolean activeAccount;





    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")   // esto toma lo primero que tiene abajo //

    private Client client;
    private String number;
    private LocalDateTime creationDate;
    private double balance;
    private AccountType accountType;
    @OneToMany(mappedBy = "account", fetch=FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    public Account(){}

    public Account(boolean activeAccount, String number, LocalDateTime creationDate,double balance, Client client, AccountType accountType){
        this.activeAccount = activeAccount;
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
        this.accountType = accountType;
    }
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public boolean isActiveAccount() {
        return activeAccount;
    }
    public void setActiveAccount(boolean activeAccount) {
        this.activeAccount = activeAccount;
    }

    public long getId() {return id;}

    public String getNumber() {return number;}

    public void setNumber(String number) {this.number = number;}
    public LocalDateTime getcreationDate() {return creationDate;}

    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

    public double getBalance() {return balance;}

    public void setBalance(double balance) {this.balance = balance;}

    public Client getClient() {return client;}

    public void setClient(Client client) {this.client = client;}
    public Set<Transaction> getTransactions() {return transactions;}

    public void addTransaction(Transaction transaction) {
        transaction.setAccount(this);
        transactions.add(transaction);
    }
}
