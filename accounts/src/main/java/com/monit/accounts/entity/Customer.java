package com.monit.accounts.entity;

import com.monit.accounts.dto.AccountsDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter@Setter@NoArgsConstructor @AllArgsConstructor @ToString
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    private String email;
    @Column(name = "mobile_number", unique=true , updatable=false)
    private String mobileNumber;
}
