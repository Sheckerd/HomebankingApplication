package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.dtos.ClientLoanDTO;
import com.mindhub.Homebanking.models.ClientLoan;
import com.mindhub.Homebanking.repositories.ClientLoanRepository;
import com.mindhub.Homebanking.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientLoanServiceImpl implements ClientLoanService {

    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Override
    public Set<ClientLoanDTO> getAllclientloansDTO() {
        return clientLoanRepository.findAll().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
    }

    @Override
    public ClientLoanDTO getClientLoan(Long id) {
        return clientLoanRepository.findById(id).map(ClientLoanDTO::new).orElse(null);
    }

    @Override
    public void saveClientLoan(ClientLoan clientLoan) {
         clientLoanRepository.save(clientLoan);
    }
}
