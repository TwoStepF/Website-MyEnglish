package com.example.englishapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusP {
    private String status;
    private String message;
    private Object data;
    public StatusP() {
    }

}
