package com.mindhub.Homebanking.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private String name;
    private int maxAmount;

    private double percentage;

    @ElementCollection
    @Column(name="payments")
    private Set<Integer> payments = new HashSet<>();

    @OneToMany(mappedBy = "loan", fetch=FetchType.EAGER)
    private Set<ClientLoan> clientloans = new HashSet<>();

    public Loan () {}

    public Loan(String name, int maxAmount, Set<Integer> payments, int percentage){
        this.percentage = percentage;
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;

    }

    public long getId() {
        return id;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Set<Integer> getPayments() {
        return payments;
    }

    public void setPayments(Set<Integer> payments) {
        this.payments = payments;
    }

    public Set<ClientLoan> getClientloans() {
        return clientloans;
    }

    public void addClientLoans(ClientLoan clientloan){
        clientloan.setLoan(this);
        clientloans.add(clientloan);
    }

}
