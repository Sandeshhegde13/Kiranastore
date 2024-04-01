package com.example.Kiranastore.repo;

import com.example.Kiranastore.models.Entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface Transactionrepo extends MongoRepository<Transaction,Integer> {

    List<Transaction> findByTransactionTimeBetweenAndTo(LocalDateTime day1, LocalDateTime day2, String to);
    List<Transaction> findByTransactionTimeBetweenAndFrom(LocalDateTime day1, LocalDateTime day2, String from);

    List<Transaction> findByMonthAndYearAndTo(int month, int year, String to);
    List<Transaction> findByMonthAndYearAndFrom(int month, int year, String from);
}
