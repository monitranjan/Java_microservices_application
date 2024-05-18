package com.monit.accounts.service.client;

import com.monit.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{

    @Override
    public ResponseEntity<LoansDto> fetchLoans(String correlationId, String mobileNumber) {
        // Todo: either implement to get data from other db or return some generic msg 
        return null;
    }

}
