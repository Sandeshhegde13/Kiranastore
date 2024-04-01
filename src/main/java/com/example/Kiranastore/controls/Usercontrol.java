package com.example.Kiranastore.controls;

import com.example.Kiranastore.models.Entity.User;
import com.example.Kiranastore.models.Response.ApiResponse;
import com.example.Kiranastore.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Kirana")
public class Usercontrol {

    @Autowired
    private Userservice service;


    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse> addUser(@RequestBody User user){
        return new ResponseEntity<>(service.addUser(user), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User>  getUser(@PathVariable String id){
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/allUser")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> user = service.getAllUser();
        return ResponseEntity.ok(user);
    }


}

