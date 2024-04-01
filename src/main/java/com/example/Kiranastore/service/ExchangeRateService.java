package com.example.Kiranastore.service;

import com.example.Kiranastore.models.ExchangeRate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateService {

    private final static String API_URL = "https://api.fxratesapi.com/latest";

    private RestTemplate restTemplate;

    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRate exchangeRate(){
        return restTemplate.getForObject(API_URL, ExchangeRate.class);
    }


}
