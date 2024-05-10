package com.monit.loans.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Loans extends BaseEntity {

    @Id
    private Long loanId;
    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private Long totalLoan;
    private Long amountPaid;
    private Long outstandingAmount;
}
