package com.example.englishapp.Controller;

import com.example.englishapp.Dto.MessageDto;
import com.example.englishapp.Dto.SigninRequest;
import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    private StatusP SendMessage(@RequestBody MessageDto messageDto){
        return messageService.SendMessage(messageDto);
    }


    @GetMapping
    private List<MessageDto> GetMessage(){
        return messageService.GetMessage();
    }
}
