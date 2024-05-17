package com.monit.accounts.service.client;

import com.monit.cards.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/fetch" , consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCard(@RequestParam String mobileNumber);

}