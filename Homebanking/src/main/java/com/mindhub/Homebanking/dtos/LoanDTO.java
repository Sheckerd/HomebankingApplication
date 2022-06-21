package com.mindhub.Homebanking.dtos;
import com.mindhub.Homebanking.models.ClientLoan;
import com.mindhub.Homebanking.models.Loan;
import java.util.HashSet;
import java.util.Set;

public class LoanDTO {
    private long id;
    private String name;
    private int maxAmount;
    private Set<Integer> payments = new HashSet<>();
    private Set<ClientLoan> clientloans = new HashSet<>();
    public LoanDTO(){}
    public LoanDTO(Loan loan){
        this.id=loan.getId();
        this.name=loan.getName();
        this.maxAmount=loan.getMaxAmount();
        this.payments=loan.getPayments();
    }

    public long getId() {
        return id;
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



}
