package com.example.Kiranastore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {

    private boolean success;

    private String terms;

    private String privacy;

    private long timestamp;

    private String date;

    private String base;

    private Map<String, Double> rates;
}
