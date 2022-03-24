package com.example.englishapp.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "vocabulary")
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long VocabularyID;

    @NotBlank
    @Column(length = 40)
    private String EnglishMeaning;

    @Column
    private String Type;

    @NotBlank
    @Column(name = "Vietnamese_mean")
    private String VietnameseMeaning;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Vocabulary() {
    }

    public Vocabulary(String englishMeaning, String type, String vietnameseMeaning, User user) {
        EnglishMeaning = englishMeaning;
        Type = type;
        VietnameseMeaning = vietnameseMeaning;
        this.user = user;
    }
}
