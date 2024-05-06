package com.monit.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(name = "Accounts", description = "Schema to hold Account information")
@Data
public class AccountsDto {
    @NotEmpty(message = "AccountNumber can not be null or empty")
    @Schema(description = "Account Number", example = "3454433243")
    private Long accountNumber;

    @Schema(description = "Account type", example = "Savings")
    @NotEmpty(message = "AccountType cannot be null or empty")
    private String accountType;

    @Schema(description = "Branch address", example = "123 NewYork")
    @NotEmpty(message = "BranchAddress cannot be null or empty")
    private String branchAddress;
}
