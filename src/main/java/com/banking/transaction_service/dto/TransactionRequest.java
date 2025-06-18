package com.banking.transaction_service.dto;

import com.banking.transaction_service.model.TransactionType;

import lombok.Data;

@Data
public class TransactionRequest {
    private Long accountId;
    private Double amount;
    private TransactionType type;
    private String description;
}
