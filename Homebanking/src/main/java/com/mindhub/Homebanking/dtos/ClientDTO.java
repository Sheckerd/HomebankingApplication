package com.mindhub.Homebanking.dtos;
import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.models.Client;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class ClientDTO{

    private long id;
    private String name, apellido, email;
    private Set<AccountDTO> accounts = new HashSet<>();
    private Set<ClientLoanDTO> clientloans = new HashSet<>();
    private Set<CardDTO> cards = new HashSet<>();

    public ClientDTO () {}
   public ClientDTO(Client client) {
       this.id = client.getId();
       this.name= client.getName();
       this.apellido = client.getApellido();
       this.email = client.getEmail();
       this.accounts = client.getAccounts().stream().filter(Account::isActiveAccount).map(AccountDTO::new).collect(Collectors.toSet());
       this.clientloans = client.getClientloans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
       this.cards = client.getCard().stream().filter(Card::isActiveCard).map(CardDTO::new).collect(Collectors.toSet());


   }



    public long getId() {return id;}

    public String getName(){
        return name;
    }


    public String getApellido() {
        return apellido;
    }


    public String getEmail() {
        return email;
    }

    public Set<AccountDTO> getAccounts() {return accounts;}

    public Set<ClientLoanDTO> getClientloans() {
        return clientloans;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public void setClientloans(Set<ClientLoanDTO> clientloans) {
        this.clientloans = clientloans;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }

    public void setCards(Set<CardDTO> cards) {
        this.cards = cards;
    }
}
