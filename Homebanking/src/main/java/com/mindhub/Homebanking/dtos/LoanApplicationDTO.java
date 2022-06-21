package com.mindhub.Homebanking.dtos;


import java.util.HashSet;
import java.util.Set;

public class LoanApplicationDTO {

    private long idLoan;
    private String name;
    private int amount;
    private int payments;
    private double percentage;
    private String destinationAccount;

    public LoanApplicationDTO () {}

    public LoanApplicationDTO(long idLoan, String name, int amount, int payments, String destinationAccount, int percentage) {
        this.idLoan = idLoan;
        this.name = name;
        this.amount = amount;
        this.payments = payments;
        this.destinationAccount = destinationAccount;
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    public long getidLoan() {
        return idLoan;
    }

    public void setIdLoan(long idLoan) {
        this.idLoan = idLoan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
