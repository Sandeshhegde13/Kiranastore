package com.example.Kiranastore.controls;

import com.example.Kiranastore.models.Entity.Transaction;
import com.example.Kiranastore.models.Response.ApiResponse;
import com.example.Kiranastore.models.TransactionRequest;
import com.example.Kiranastore.service.Transactionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Kirana")
public class Transactioncontrol {

    @Autowired
    private Transactionservice tservice;

    @PostMapping("/addtransactions")
    public ResponseEntity<ApiResponse> addtransaction(@RequestBody TransactionRequest data){
        return new ResponseEntity<>(tservice.handletransaction(data), HttpStatus.OK);

    }

    @GetMapping("/getall")
    public ResponseEntity<List<Transaction>>getalltransactions(){
        List<Transaction> transactions = tservice.getalltransactions();
        return ResponseEntity.ok(transactions);

    }


}
