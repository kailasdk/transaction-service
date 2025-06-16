package com.banking.transaction_service.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.banking.transaction_service.model.Transaction;
import com.banking.transaction_service.service.TransactionService;

import java.util.List;
// C:\Users\kkendre\Desktop\private\transaction-service\src\main\resources\bootstrap.yml
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public List<Transaction> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getByAccountId(@PathVariable Long accountId) {
        return service.getByAccountId(accountId);
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction txn) {
        return service.create(txn);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
