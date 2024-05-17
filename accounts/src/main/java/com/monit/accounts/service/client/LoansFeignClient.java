package com.monit.accounts.service.client;

import com.monit.accounts.dto.LoansDto;
import com.monit.cards.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch" , consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoans(@RequestHeader("correlation-id") String correlationId,
                                               @RequestParam String mobileNumber);

}
