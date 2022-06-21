package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.ClientLoanDTO;
import com.mindhub.Homebanking.models.ClientLoan;

import java.util.Set;

public interface ClientLoanService {
    Set<ClientLoanDTO> getAllclientloansDTO();
    ClientLoanDTO getClientLoan(Long id);
    void saveClientLoan(ClientLoan clientLoan);


}
