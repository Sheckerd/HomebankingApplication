package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.models.CardColor;
import com.mindhub.Homebanking.models.CardType;

import java.time.LocalDateTime;

public class CardDTO {
    private long id;
    private boolean cardExpired;
    private boolean activeCard;
    private CardType type;
    private CardColor color;
    private long number1;
    private long number2;
    private long number3;
    private long number4;
    private int cvv;
    private LocalDateTime fromDate;
    private LocalDateTime thruDate;
    private String cardholder;

    public CardDTO(){}

    public CardDTO(Card card){

        this.cardExpired = card.isCardExpired();
        this.activeCard = card.isActiveCard();
        this.id = card.getId();
        this.type = card.getType();
        this.color = card.getColor();
        this.number1 = card.getNumber1();
        this.number2 = card.getNumber2();
        this.number3 = card.getNumber3();
        this.number4 = card.getNumber4();
        this.cvv = card.getCvv();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
        this.cardholder = card.getCardholder();

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

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public long getNumber1() {
        return number1;
    }

    public void setNumber1(long number1) {
        this.number1 = number1;
    }

    public long getNumber2() {
        return number2;
    }

    public void setNumber2(long number2) {
        this.number2 = number2;
    }

    public long getNumber3() {
        return number3;
    }

    public void setNumber3(long number3) {
        this.number3 = number3;
    }

    public long getNumber4() {
        return number4;
    }

    public void setNumber4(long number4) {
        this.number4 = number4;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDateTime thruDate) {
        this.thruDate = thruDate;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }
}
