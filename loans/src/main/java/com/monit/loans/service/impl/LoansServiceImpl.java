package com.monit.loans.service.impl;

import com.monit.loans.constants.LoansConstants;
import com.monit.loans.dto.LoansDto;
import com.monit.loans.entity.Loans;
import com.monit.loans.exception.LoansAlreadyExistException;
import com.monit.loans.exception.ResourceNotFoundException;
import com.monit.loans.mapper.LoansMapper;
import com.monit.loans.repository.LoansRepository;
import com.monit.loans.service.ILoansService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {

    private final LoansRepository loansRepository;

    public LoansServiceImpl(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()) {
            throw new LoansAlreadyExistException("Loan already exist with mobile number " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomNumber = 10000000L + new Random().nextInt(90000000);
        newLoan.setLoanNumber(String.valueOf(randomNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan((long) LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0L);
        newLoan.setOutstandingAmount((long) LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans= loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loans","Mobile Number",mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans,new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans= loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Loans","Mobile Number",loansDto.getMobileNumber())
        );
        LoansMapper.mapToLoans(loansDto,loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "Mobile Number", mobileNumber)
        );
        if (null != loans) {
            loansRepository.deleteById(loans.getLoanId());
        }
        return true;
    }
}
