package com.monit.accounts.service;

import com.monit.accounts.dto.CustomerDetailsDto;
import org.springframework.stereotype.Service;

public interface ICustomerService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
