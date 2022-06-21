package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.models.Loan;

import java.util.Set;

public interface LoanService {

    Set<LoanDTO> getLoansDTO();
    Loan getLoanById(long id);


    void saveLoan(Loan loan);
}
