package com.mindhub.Homebanking.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private boolean cardExpired;
    private boolean activeCard;
    private String cardholder;
    private CardType type;
    private CardColor color;
    private long number1;
    private long number2;
    private long number3;
    private long number4;
    private int cvv;
    private LocalDateTime fromDate;
    private LocalDateTime thruDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client.id")
    private Client client;

    public Card(){}



    public Card(boolean cardExpired, boolean activeCard, String cardholder, CardType type, CardColor color, long number1, long number2, long number3, long number4, int cvv, LocalDateTime fromDate, LocalDateTime thruDate, Client client) {
        this.cardExpired = cardExpired;
        this.activeCard = activeCard;
        this.cardholder = cardholder;
        this.type = type;
        this.color = color;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.cvv = cvv;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.client = client;
    }

    public boolean isCardExpired() {
        return cardExpired;
    }

    public void setCardExpired(boolean cardExpired) {
        this.cardExpired = cardExpired;
    }

    public boolean isActiveCard() {
        return activeCard;
    }

    public void setActiveCard(boolean activeCard) {
        this.activeCard = activeCard;
    }

    public long getId() {
        return id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public CardType getType() {
        return type;
    }

    public CardColor getColor() {
        return color;
    }

    public long getNumber1() {
        return number1;
    }

    public long getNumber2() {
        return number2;
    }

    public long getNumber3() {
        return number3;
    }

    public long getNumber4() {
        return number4;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
