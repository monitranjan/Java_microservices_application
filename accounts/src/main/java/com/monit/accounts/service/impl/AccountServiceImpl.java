package com.monit.accounts.service.impl;

import com.monit.accounts.constants.AccountConstants;
import com.monit.accounts.dto.AccountsDto;
import com.monit.accounts.dto.CustomerDto;
import com.monit.accounts.entity.Accounts;
import com.monit.accounts.entity.Customer;
import com.monit.accounts.exception.CustomerAlreadyExitsException;
import com.monit.accounts.exception.ResourceNotFoundException;
import com.monit.accounts.mapper.AccountsMapper;
import com.monit.accounts.mapper.CustomerMapper;
import com.monit.accounts.repository.AccountRepository;
import com.monit.accounts.repository.CustomerRepository;
import com.monit.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.maptoCustomer(customerDto,new Customer());
    Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if(optionalCustomer.isPresent()){
        throw new CustomerAlreadyExitsException("Customer already exists with mobileNumber: " + customerDto.getMobileNumber());
    }
    Customer savedCustomer = customerRepository.save(customer);
    accountRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 100000000L+(int)(Math.random()*90000000);
        accounts.setAccountNumber(randomAccountNumber);
        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);
        return accounts;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Accounts","customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.maptoCustomerDto(customer,new CustomerDto());
        customerDto.setAccountDetails(AccountsMapper.maptoAccountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountDetails();
        if(null != accountsDto){
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account", "AccountNumber" ,accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.maptoAccounts(accountsDto ,accounts);
            accounts = accountRepository.save(accounts);

            Long CustomerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(CustomerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer", "CustomerId" ,CustomerId.toString())
            );
            CustomerMapper.maptoCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber" ,mobileNumber)
        );
        if(null != customer){
            accountRepository.deleteByCustomerId(customer.getCustomerId());
            customerRepository.deleteById(customer.getCustomerId());
        }
        return true;
    }


}
