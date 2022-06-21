package com.mindhub.Homebanking.services;
import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.models.Card;

import java.util.Set;

public interface CardService {
    Set<CardDTO> getCards();
    Card getCard(Long id);
    void saveCard(Card card);
}
