package com.example.Kiranastore.models;

import lombok.Data;

@Data
public class TransactionRequest {

    private String from;
    private String to;
    private Double amount;
    private String initialCurrency;
}
