package com.banking.transactionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account-service")
public interface AccountClient {
    @PutMapping("/accounts/{id}/balance")
    boolean updateBalance(
            @PathVariable("id") Long accountId,
            @RequestParam("amount") Double amount,
            @RequestParam("isCredit") boolean isCredit);
}
