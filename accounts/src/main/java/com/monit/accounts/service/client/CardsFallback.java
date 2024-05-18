package com.monit.accounts.service.client;

import com.monit.accounts.dto.LoansDto;
import com.monit.cards.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{

    @Override
    public ResponseEntity<CardsDto> fetchCard(String correlationId, String mobileNumber) {
        // Todo: either implement to get data from other db or return some generic msg 
        return null;
    }
}
