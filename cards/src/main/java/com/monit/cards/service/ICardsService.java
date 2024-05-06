package com.monit.cards.service;

import com.monit.cards.dto.CardsDto;
import com.monit.cards.entity.Cards;

public interface ICardsService {
    void createCard(String mobileNumber);
    CardsDto fetchCard(String mobileNumber);
    boolean updateCard(CardsDto cardsDto);
    boolean deleteCard(String mobileNumber);
}
