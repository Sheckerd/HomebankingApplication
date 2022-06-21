package com.mindhub.Homebanking.services.implement;
import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Set<ClientDTO> getClientsDTO(){
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toSet());
    }

    @Override
    public ClientDTO getClientDTO(long id) {
        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);
    }

    @Override
    public Client getClientCurrent(Authentication authentication) {
        return  clientRepository.findByEmail(authentication.getName());

    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }


    @Override
    public void saveClient(Client client) {
             clientRepository.save(client);
    }


}
