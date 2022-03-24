package com.example.englishapp.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name = "tokeninfor")
@Data
public class TokenInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotBlank
    private String username;

    private Date time;

    public TokenInfor(String username, Date time) {
        this.username = username;
        this.time = time;
    }

    public TokenInfor() {

    }
}
