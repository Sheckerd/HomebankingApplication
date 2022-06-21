package com.mindhub.Homebanking.controllers;
import com.mindhub.Homebanking.dtos.ClientLoanDTO;
import com.mindhub.Homebanking.repositories.ClientLoanRepository;
import com.mindhub.Homebanking.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientLoanController {

    @Autowired
    private ClientLoanService clientLoanService;

    @RequestMapping("/clientloans")
    public Set<ClientLoanDTO> getAllclientloans(){
        return clientLoanService.getAllclientloansDTO();
    }

    @RequestMapping("/clientloans/{id}")
    public ClientLoanDTO getClientLoan(@PathVariable Long id){
       return clientLoanService.getClientLoan(id);
    }


}
