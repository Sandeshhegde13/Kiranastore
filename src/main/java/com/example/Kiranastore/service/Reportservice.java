package com.example.Kiranastore.service;

import com.example.Kiranastore.models.Entity.Transaction;
import com.example.Kiranastore.models.Response.ApiResponse;
import com.example.Kiranastore.models.Response.MonthlyReport;
import com.example.Kiranastore.models.Response.WeeklyReport;
import com.example.Kiranastore.repo.Transactionrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.clamp;
import static java.lang.Math.round;

@Service
public class Reportservice {

    @Autowired
    private Transactionrepo trepo;

    @Autowired
    private Transactionservice tservice;

    public Double amountSum(List<Transaction> transactions){
        Double sum = 0.0;
        for(Transaction transaction: transactions){
            sum += transaction.getAmount();
        }
        return sum;


    }

    private Double round(Double value, int decimalPlaces){
        if(value == null){
            return null;
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static final Map<Integer, Integer> monthDaysMap = new HashMap<Integer,Integer>(){{
        put(1, 31); // January
        put(2, 28); // February (non-leap year)
        put(3, 31); // March
        put(4, 30); // April
        put(5, 31); // May
        put(6, 30); // June
        put(7, 31); // July
        put(8, 31); // August
        put(9, 30); // September
        put(10, 31); // October
        put(11, 30); // November
        put(12, 31); // December
    }};



    public ApiResponse getWeekReportApiResponse(String userid){
        ApiResponse response = new ApiResponse();
        WeeklyReport weekreport = createWeeklyReportOfUser(userid);
        response.setData(weekreport);
        return response;
    }

    public WeeklyReport createWeeklyReportOfUser(String userid){
        List<Transaction> weeklyCredit = tservice.getCreditTransOfLastWeek(userid);
        List<Transaction> weeklyDebit = tservice.getDebitTransOfLastWeek(userid);

        Double totalCreditedAmount = round(amountSum(weeklyCredit),2);
        Double totalDebitedAmount = round(amountSum(weeklyDebit),2);

        Double totalDays = 7.0;
        Double totalAmount = totalCreditedAmount+totalDebitedAmount;
        Double averageCredit = round(totalCreditedAmount / totalDays, 2);
        Double averageDebit = round(totalDebitedAmount / totalDays, 2);
        Double averageTransaction = round(totalAmount / totalDays, 2);
        Double netAmount = totalCreditedAmount-totalDebitedAmount;

        WeeklyReport report = new WeeklyReport();

        report.setNetProfit(netAmount);
        report.setTotalTransaction(totalAmount);
        report.setTotalDebit(totalDebitedAmount);
        report.setTotalCredit(totalCreditedAmount);
        report.setAverageCredit(averageCredit);
        report.setAverageDebit(averageDebit);
        report.setAverageTransaction(averageTransaction);

        return report;
    }

    public ApiResponse getMonthlyReportApiResponse(int month, String userid){
        ApiResponse response = new ApiResponse();
        LocalDateTime currentTime = LocalDateTime.now();
        int year = currentTime.getYear();
        MonthlyReport report = createMonthlyReportOfUser(month,year,userid);
        response.setData(report);
        return response;
    }

    public MonthlyReport createMonthlyReportOfUser(int month, int year,String userid){
        List<Transaction> monthlyCredit = tservice.getCreditTransactionOfLastMonth(month,year,userid);
        List<Transaction> monthlyDebit = tservice.getDebitTransactionOfLastMonth(month,year,userid);

        Double totalCreditAmount = round(amountSum(monthlyCredit), 2);
        Double totalDebitAmount = round(amountSum(monthlyDebit), 2);

        // Calculate total amount, average credit, average debit, and average transaction
        Double totalAmount = totalDebitAmount + totalCreditAmount;
        Double averageCredit = round(totalCreditAmount / monthDaysMap.get(month), 2);
        Double averageDebit = round(totalDebitAmount / monthDaysMap.get(month), 2);
        Double averageTransaction = round(totalAmount / monthDaysMap.get(month), 2);

        // Create and return MonthlyReport
        MonthlyReport report = new MonthlyReport();
        report.setTotalTransaction(totalAmount);
        report.setTotalDebit(totalDebitAmount);
        report.setTotalCredit(totalCreditAmount);
        report.setAverageCredit(averageCredit);
        report.setAverageDebit(averageDebit);
        report.setAverageTransaction(averageTransaction);

        return report;
    }
}
