package com.banking.transactionservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.transactionservice.model.Transaction;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
List<Transaction> findByFromAccountIdOrToAccountId(Long fromAccountId, Long toAccountId);
}



