package com.example.Kiranastore.models.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Transaction {

    @Id
    private String transactionId;
    private String from;
    private Double amount;
    private String initialCurrency;
    private String finalCurrency;
    private String to;
    private int day;
    private int month;
    private int year;
    private LocalDateTime transactionTime;
}
