package com.mindhub.Homebanking.controllers;

import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping("/clients")
    public Set<ClientDTO> getAll() {
        return clientService.getClientsDTO();
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientDTO(id);
    }

    @RequestMapping("/clients/current")

    public ClientDTO getUsername(Authentication authentication) {

        Client client = clientService.getClientCurrent(authentication);
        return new ClientDTO(client);

    }


    @Autowired

    private PasswordEncoder passwordEncoder;


    @RequestMapping(path = "/clients", method = RequestMethod.POST)

    public ResponseEntity<Object> register(

            @RequestParam String name, @RequestParam String apellido,

            @RequestParam String email, @RequestParam String password) {


        if (name.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }


        if (clientService.getClientByEmail(email) != null) {

            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);

        }


        Client client = new Client(name, apellido, email, passwordEncoder.encode(password));
        clientService.saveClient(client);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }


}
