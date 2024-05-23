package com.monit.accounts.functions;

import com.monit.accounts.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;


@Configuration
public class AccountFunctions {
    private static final Logger log = LoggerFactory.getLogger(AccountFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountService accountService){
        return accountNumber->{
            log.info("updating Communication status for the account number:" + accountNumber.toString());
            accountService.updateCommunication(accountNumber);
        };
    }
}
