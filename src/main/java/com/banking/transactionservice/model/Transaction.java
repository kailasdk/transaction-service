package com.banking.transactionservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AccountId;

    private Long fromAccountId; // Nullable for deposits
    private Long toAccountId; // Nullable for withdrawals
    private Double amount;

    private LocalDateTime timestamp;

    private TransactionType type; // "DEPOSIT", "WITHDRAWAL", "TRANSFER"
    private String status; // "SUCCESS", "FAILED"

    private String description;
}
