package com.example.Kiranastore.service;

import com.example.Kiranastore.models.Entity.Transaction;
import com.example.Kiranastore.models.ExchangeRate;
import com.example.Kiranastore.models.Response.ApiResponse;
import com.example.Kiranastore.models.TransactionRequest;
import com.example.Kiranastore.repo.Transactionrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class Transactionservice {

    private final Transactionrepo trepo;

    private final ExchangeRateService exchangerateservice;

    @Autowired
    public Transactionservice(Transactionrepo trepo, ExchangeRateService exchangerateservice){
        this.trepo=trepo;
        this.exchangerateservice=exchangerateservice;
    }

    public Transaction addTransaction(TransactionRequest data){
        Transaction transaction = new Transaction();
        transaction.setInitialCurrency(data.getInitialCurrency());
        transaction.setFrom(data.getFrom());
        transaction.setTo(data.getTo());

        String currencyType = transaction.getInitialCurrency().toUpperCase();
        transaction.setInitialCurrency(currencyType);
        transaction.setFinalCurrency("USD");

        ExchangeRate exchangeRatedata = exchangerateservice.exchangeRate();
        Map<String, Double> rates = exchangeRatedata.getRates();
        Double convertedAmount = data.getAmount()/ rates.get(currencyType);
        BigDecimal finalamount = new BigDecimal(convertedAmount).setScale(2, RoundingMode.HALF_UP);
        transaction.setAmount(finalamount.doubleValue());

        LocalDateTime time = LocalDateTime.now();
        transaction.setTransactionTime(time);
        int day = time.getDayOfYear();
        int month = time.getMonthValue();
        int year = time.getYear();
        transaction.setDay(day);
        transaction.setMonth(month);
        transaction.setYear(year);
        return trepo.save(transaction);

    }

    public ApiResponse handletransaction(TransactionRequest data){
        Transaction completetransaction = addTransaction(data);
        ApiResponse response = new ApiResponse();
        response.setDisplayMessage("Transacation successfully addded");
        response.setData(completetransaction);
        return response;

    }

    public List<Transaction> getalltransactions(){
        return trepo.findAll();
    }

    public List<Transaction> getCreditTransOfLastWeek(String userid){
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startPoint = currentTime.minusDays(7);

        return trepo.findByTransactionTimeBetweenAndTo(startPoint,currentTime,userid);

    }

    public List<Transaction> getDebitTransOfLastWeek(String userid){
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startPoint = currentTime.minusDays(7);

        return trepo.findByTransactionTimeBetweenAndFrom(startPoint,currentTime,userid);
    }

    public List<Transaction> getCreditTransactionOfLastMonth(int month,int year,String userid){
        return trepo.findByMonthAndYearAndTo(month,year,userid);
    }

    public List<Transaction> getDebitTransactionOfLastMonth(int month, int year, String userid){
        return trepo.findByMonthAndYearAndFrom(month,year,userid);
    }

}