package com.sbrf.reboot;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.NonNull;
import lombok.val;

import java.io.IOException;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean isClientHasContract(
            @NonNull long clientId,
            @NonNull long contractNumber) throws IOException {
        val allAccounts = accountRepository.getAllAccountsByClientId(clientId);
        return allAccounts.contains(contractNumber);
    }

    public boolean isClientHasPaymentHistory(
            @NonNull long clientId) {
        val paymentHistory = accountRepository.getAllPaymentDetails(clientId);
        return !paymentHistory.isEmpty();
    }
}
