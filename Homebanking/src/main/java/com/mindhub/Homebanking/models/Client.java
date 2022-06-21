package com.mindhub.Homebanking.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private String name;
    private String apellido;
    private String email;

    private String password;

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch=FetchType.EAGER)
    private Set<ClientLoan> clientloans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch=FetchType.EAGER )
    private Set<Card> cards = new HashSet<>();

     public Client () {}

    public Client (String name, String apellido, String email, String password){
         this.name = name;
         this.apellido = apellido;
         this.email = email;
         this.password = password;
    }

   public long getId() {return id;}

   public String getName(){
         return name;
   }

    public void setName(String name) {
        this.name = name;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<Account> getAccounts() {return accounts;}
    public void addAccount(Account account) {
        account.setClient(this);
        accounts.add(account);
    }

    public Set<ClientLoan> getClientloans() {
        return clientloans;
    }

    public void addClientLoan(ClientLoan clientloan){clientloan.setClient(this);
       clientloans.add(clientloan);
    }

    public Set<Card> getCard() {
        return cards;
    }

    public void addCard(Card card){
         card.setClient(this);
         cards.add(card);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
