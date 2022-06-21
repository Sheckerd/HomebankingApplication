package com.mindhub.Homebanking.dtos;
import com.mindhub.Homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private long idClientLoan;

    private long idLoan;
    private Double amount;
    private int payments;
    private double percentage;
    private String name;

    public ClientLoanDTO () {}

    public ClientLoanDTO(ClientLoan clientloan){
        this.idClientLoan = clientloan.getId();
        this.idLoan = clientloan.getLoan().getId();
        this.amount = clientloan.getAmount();
        this.payments = clientloan.getPayments();
        this.name = clientloan.getLoan().getName();
        this.percentage = clientloan.getLoan().getPercentage();
    }

    public long getIdClientLoan() {
        return idClientLoan;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public long getIdLoan() {
        return idLoan;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getPayment() {
        return payments;
    }

    public void setPayment(int payment) {
        this.payments = payments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
