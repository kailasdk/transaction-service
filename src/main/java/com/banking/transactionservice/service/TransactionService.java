package com.banking.transactionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.transactionservice.client.AccountClient;
import com.banking.transactionservice.model.Transaction;
import com.banking.transactionservice.model.TransactionType;
import com.banking.transactionservice.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private AccountClient accountClient;

    public Transaction create(Transaction transaction) {
        boolean success = accountClient.updateBalance(
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getType() == TransactionType.CREDIT);
        if (!success) {
            throw new RuntimeException("Failed to update account balance");
        }

        transaction.setTimestamp(LocalDateTime.now());
        return repository.save(transaction);
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Transaction> getByAccountId(Long accountId) {
        return repository.findByFromAccountIdOrToAccountId(accountId, accountId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}