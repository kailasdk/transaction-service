package com.banking.transactionservice.client;

import org.springframework.stereotype.Component;

@Component
public class AccountClient {
    public boolean updateBalance(Long accountId, Double amount, boolean isCredit) {
        // Mocked logic: just log and return true
        System.out.printf("Updating account %d: %s %.2f%n", accountId, isCredit ? "credit" : "debit", amount);
        return true;
    }
}
