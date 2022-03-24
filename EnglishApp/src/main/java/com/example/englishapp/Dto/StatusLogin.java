package com.example.englishapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusLogin {
    private String status;
    private String message;
    private String authenticationToken;
    private String username;
    private String role;
}
