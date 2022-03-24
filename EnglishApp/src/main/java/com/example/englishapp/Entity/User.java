package com.example.englishapp.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "email", unique=true, length = 55)
    @NotBlank
    private String Email;

    @Column(name = "username", unique=true, length = 16)
    @NotBlank
    private String Username;

    @Column(name = "password")
    @NotBlank
    private String Password;

    @Column(name = "role")
    @NotBlank
    private String Role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Vocabulary> vocabularies;

    public User(String userName, String encrytedPassword, String email) {
        this.Username = userName;
        this.Password = encrytedPassword;
        this.Role = "ROLE_USER";
        this.Email = email;
    }

    public User() {

    }
}
