package com.monit.loans.mapper;

import com.monit.loans.dto.LoansDto;
import com.monit.loans.entity.Loans;

public class LoansMapper {
    public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        loansDto.setAmountPaid(loans.getAmountPaid());
        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setLoanType(loansDto.getLoanType());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        loans.setAmountPaid(loansDto.getAmountPaid());
        return loans;
    }
}
