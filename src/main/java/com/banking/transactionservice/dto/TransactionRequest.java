package com.banking.transactionservice.dto;

import com.banking.transactionservice.model.TransactionType;

import lombok.Data;

@Data
public class TransactionRequest {
    private Long accountId;
    private Double amount;
    private TransactionType type;
    private String description;
}
