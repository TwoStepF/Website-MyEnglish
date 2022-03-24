package com.example.englishapp.Controller;

import com.example.englishapp.Dto.SigninRequest;
import com.example.englishapp.Dto.StatusData;
import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Dto.VocabularyDTO;

import com.example.englishapp.Service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Vocabulary")
public class VocabularyController {

    @Autowired
    private VocabularyService vocabularyService;
    @PostMapping("/new")
    public StatusP CreateVocabulary(@RequestBody VocabularyDTO vocabularyDTO){
        return vocabularyService.creatVocabulary(vocabularyDTO);
    }

    @GetMapping
    public ResponseEntity<List<VocabularyDTO>> GetAllVocabularyOfUser(){
        return new ResponseEntity<>(vocabularyService.getAllVocabulary(), HttpStatus.OK);
    }

    @PostMapping("/find/english")
    public List<StatusData> FindVocabularyOfUser(@RequestBody VocabularyDTO vocabularyDTO){
        return vocabularyService.findVocabularyByEnglishMean(vocabularyDTO);
    }

    @PostMapping("/find/vietnam")
    public List<StatusData> FindVocabularyOfUserV(@RequestBody VocabularyDTO vocabularyDTO){
        return vocabularyService.findVocabularyByVietnamemean(vocabularyDTO);
    }
}
