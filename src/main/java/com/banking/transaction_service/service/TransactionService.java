package com.banking.transaction_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.transaction_service.model.Transaction;
import com.banking.transaction_service.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Transaction> getByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }

    public Transaction create(Transaction txn) {
        txn.setTransactionTime(LocalDateTime.now());
        return repository.save(txn);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}