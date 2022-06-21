package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.models.Loan;
import com.mindhub.Homebanking.repositories.LoanRepository;
import com.mindhub.Homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;


    @Override
    public Set<LoanDTO> getLoansDTO() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toSet());
    }

    @Override
    public Loan getLoanById(long id) {
        return loanRepository.findById(id);
    }


    @Override
    public void saveLoan(Loan loan) {
      loanRepository.save(loan);
    }
}
