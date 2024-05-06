package com.monit.loans.service;

import com.monit.loans.dto.LoansDto;
import com.monit.loans.entity.Loans;

public interface ILoansService {
    void createLoan(String MobileNumber);
    LoansDto fetchLoan(String MobileNumber);
    boolean updateLoan(LoansDto loansDto);
    boolean deleteLoan(String MobileNumber);
}
