package com.exercise.bankapplication.application.bankaccount.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BankAccountDTO {
    private final int numberAccount;
    private final String typeAccount;
    private final double balanceStart;
    private final boolean status;
    private final String nameClient;
}
