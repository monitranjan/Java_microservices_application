package com.monit.cards.service.serviceImpl;

import com.monit.cards.constants.CardsConstants;
import com.monit.cards.dto.CardsDto;
import com.monit.cards.entity.Cards;
import com.monit.cards.exception.CardAlreadyExistsException;
import com.monit.cards.exception.ResourceNotFoundException;
import com.monit.cards.mapper.CardsMapper;
import com.monit.cards.repository.CardsRepository;
import com.monit.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {
    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
    Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
    if (cards.isPresent()) {
        throw new CardAlreadyExistsException("card already registered with this number" + mobileNumber);
    }
    cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards cards = new Cards();
        long randomCardNumber = 100000L + new Random().nextInt(900000);
        cards.setMobileNumber(mobileNumber);
        cards.setCardNumber(randomCardNumber);
        cards.setCardType(CardsConstants.CREDIT_CARD);
        cards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        cards.setAmountUsed(0);
        cards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return cards;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(()->
                new ResourceNotFoundException("card","mobileNumber",mobileNumber));
        return CardsMapper.maptoCardsDto(cards,new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("card","mobileNumber",cardsDto.getMobileNumber())
        );
        CardsMapper.maptoCards(cardsDto,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("card","mobileNumber",mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
