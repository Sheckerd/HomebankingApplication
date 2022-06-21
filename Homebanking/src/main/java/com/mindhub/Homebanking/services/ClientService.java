package com.mindhub.Homebanking.services;
import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.models.Client;
import org.springframework.security.core.Authentication;
import java.util.Set;

public interface ClientService {
    Set<ClientDTO> getClientsDTO();

    ClientDTO getClientDTO(long id);

    Client getClientCurrent(Authentication authentication);

    Client getClientByEmail(String email);

    void saveClient(Client client);



}
