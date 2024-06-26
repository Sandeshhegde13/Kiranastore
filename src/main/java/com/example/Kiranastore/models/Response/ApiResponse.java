package com.example.Kiranastore.models.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private boolean success = true;
    private Object data;
    private Object view;
    private String status;
    private String error;
    private Object errorMessage;
    public String displayMessage;

}
