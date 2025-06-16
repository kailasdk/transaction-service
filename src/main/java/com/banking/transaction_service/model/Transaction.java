package com.banking.transaction_service.model;

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
    private Long id;

    private Long accountId;

    private String transactionType; // e.g. DEPOSIT or WITHDRAW

    private Double amount;

    private LocalDateTime transactionTime;
}
