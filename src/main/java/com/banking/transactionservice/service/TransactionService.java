package com.banking.transactionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.transactionservice.client.AccountClient;
import com.banking.transactionservice.model.Transaction;
import com.banking.transactionservice.model.TransactionStatus;
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
        Long accountId = null;
        boolean isDeposit = false;

        if (transaction.getType() == TransactionType.DEPOSIT) {
            accountId = transaction.getToAccountId();
            isDeposit = true;

            boolean success = accountClient.updateBalance(accountId, transaction.getAmount(), true);
            if (!success) {
                throw new RuntimeException("Deposit failed: could not credit the account");
            }

        } else if (transaction.getType() == TransactionType.WITHDRAWAL) {
            accountId = transaction.getFromAccountId();
            isDeposit = false;

            boolean success = accountClient.updateBalance(accountId, transaction.getAmount(), false);
            if (!success) {
                throw new RuntimeException("Withdrawal failed: could not debit the account");
            }

        } else if (transaction.getType() == TransactionType.TRANSFER) {
            boolean debitSuccess = accountClient.updateBalance(transaction.getFromAccountId(), transaction.getAmount(), false);
            boolean creditSuccess = accountClient.updateBalance(transaction.getToAccountId(), transaction.getAmount(), true);

            if (!debitSuccess || !creditSuccess) {
                throw new RuntimeException("Transfer failed: could not update one or both accounts");
            }
        }

        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.SUCCESS);
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
