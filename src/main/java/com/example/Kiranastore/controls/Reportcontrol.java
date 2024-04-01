package com.example.Kiranastore.controls;

import com.example.Kiranastore.models.Entity.Transaction;
import com.example.Kiranastore.models.Response.ApiResponse;
import com.example.Kiranastore.service.Reportservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Kirana")
public class Reportcontrol {

    @Autowired
    private Reportservice rservice;

    @GetMapping("/weekly/{userid}")
    public ResponseEntity<ApiResponse> getweekreport(@PathVariable String userid){

        return ResponseEntity.ok(rservice.getWeekReportApiResponse(userid));

    }

    @GetMapping("/monthly/{userid}")
    public ResponseEntity<ApiResponse> getMonthlyReport(@PathVariable String userid, @RequestParam int month){
        return ResponseEntity.ok(rservice.getMonthlyReportApiResponse(month,userid));

    }


}
