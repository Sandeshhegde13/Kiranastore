package com.example.Kiranastore.service;

import com.example.Kiranastore.models.Entity.User;
import com.example.Kiranastore.models.Response.ApiResponse;
import com.example.Kiranastore.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Userservice {

    @Autowired
    private Userrepo repo;

    public ApiResponse addUser(User user){
        User data = repo.save(user);
        ApiResponse response = new ApiResponse();
        response.setData(data);
        response.setDisplayMessage("Successfully Added");
        return response;
    }

    public User getUser(String id){
        User user = repo.findById(id).orElse(null);
        return user;
    }

    public List<User> getAllUser(){
        return repo.findAll();
    }

}
