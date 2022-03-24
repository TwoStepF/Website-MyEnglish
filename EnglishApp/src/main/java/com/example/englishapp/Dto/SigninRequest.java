package com.example.englishapp.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninRequest {
    private String username;
    private String password;
    private String email;
}
