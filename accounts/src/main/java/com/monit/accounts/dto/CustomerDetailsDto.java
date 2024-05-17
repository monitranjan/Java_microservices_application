package com.monit.accounts.dto;

import com.monit.cards.dto.CardsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "CustomerDetails", description = "Schema to hold customer, cards, loans and account information")
public class CustomerDetailsDto {
    @Schema(description = "Name of Customer", example = "Monit Ranjan")
    @NotEmpty(message = "Name cannot be null or Empty")
    @Size(min=5,max = 30,message = "Name length should be between 5 and 30")
    private String name;

    @Schema(description = "Email address of the customer", example = "monitranjan2050@gmail.com")
    @NotEmpty(message = "Email cannot be null or Empty")
    @Email(message = "Email should be valid value")
    private String email;

    @Pattern(regexp = "(^|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Cards details of the Customer")
    private CardsDto cardsDetails;

    @Schema(description = "Account details of the Customer")
    private AccountsDto accountDetails;

    @Schema(description = "Loans details of the Customer")
    private LoansDto loansDetails;
}
