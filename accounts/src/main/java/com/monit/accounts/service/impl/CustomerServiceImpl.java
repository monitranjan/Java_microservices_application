package com.monit.accounts.service.impl;

import com.monit.accounts.dto.AccountsDto;
import com.monit.accounts.dto.CustomerDetailsDto;
import com.monit.accounts.dto.LoansDto;
import com.monit.accounts.entity.Accounts;
import com.monit.accounts.entity.Customer;
import com.monit.accounts.exception.ResourceNotFoundException;
import com.monit.accounts.mapper.AccountsMapper;
import com.monit.accounts.mapper.CustomerMapper;
import com.monit.accounts.repository.AccountRepository;
import com.monit.accounts.repository.CustomerRepository;
import com.monit.accounts.service.ICustomerService;
import com.monit.accounts.service.client.CardsFeignClient;
import com.monit.accounts.service.client.LoansFeignClient;
import com.monit.cards.dto.CardsDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Accounts","customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.maptoCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountDetails(AccountsMapper.maptoAccountsDto(accounts,new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoans(correlationId, mobileNumber);
        if (null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDetails(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardssDtoResponseEntity = cardsFeignClient.fetchCard(correlationId, mobileNumber);
        if (null != cardssDtoResponseEntity) {
            customerDetailsDto.setCardsDetails(cardssDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
