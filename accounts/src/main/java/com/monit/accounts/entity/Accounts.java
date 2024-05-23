package com.monit.accounts.entity;

import com.monit.accounts.dto.AccountsDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter@Setter@NoArgsConstructor @AllArgsConstructor @ToString
public class Accounts extends BaseEntity {
    private long customerId;
    @Id
    private long accountNumber;
    private String accountType;
    private String branchAddress;
    @Column(name = "communication_sw")
    private Boolean communicationSw;

}
