package com.banking.transaction_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.banking.transaction_service.model.Transaction;
import com.banking.transaction_service.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return service.create(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsByAccount(@PathVariable Long accountId) {
        return service.getByAccountId(accountId);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        service.delete(id);
    }
}
