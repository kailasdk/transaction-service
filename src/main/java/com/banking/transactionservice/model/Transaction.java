package com.banking.transactionservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private Long transactionId;

    @Column(nullable = false)
    private Long accountId;  // Main account (debited or credited)

    private Long fromAccountId;  // For TRANSFER
    private Long toAccountId;    // For TRANSFER

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private Double amount;

    @NotNull
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType type;  // DEPOSIT, WITHDRAWAL, TRANSFER

    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionStatus status;  // SUCCESS, PENDING, FAILED

    private String currency = "INR";  // Default to INR, make dynamic if needed

    @Size(max = 255)
    private String description;

    @Column(unique = true, nullable = false, updatable = false)
    private String referenceNumber;  // Unique transaction reference
}
