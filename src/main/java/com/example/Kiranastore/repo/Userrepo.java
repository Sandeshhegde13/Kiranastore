package com.example.Kiranastore.repo;

import com.example.Kiranastore.models.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface Userrepo extends MongoRepository<User,String> {
//    User findByUserName(String username);
}
