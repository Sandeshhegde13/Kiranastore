package com.example.Kiranastore.models.Entity;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@Data
public class User {

    @Id
    private String id;
    private String name;
    private String number;

}
