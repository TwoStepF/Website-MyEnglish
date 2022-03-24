package com.example.englishapp.Service;

import com.example.englishapp.Dto.MessageDto;
import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Entity.Message;
import com.example.englishapp.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private com.example.englishapp.Security.jwtToken jwtToken;

    public StatusP SendMessage(MessageDto messageDto) {
        Message message = new Message();
        try {
            if(jwtToken.validateToken(messageDto.getToken())){
                message.setMessageContent(messageDto.getMessage());
                message.setUsername(jwtToken.getUsernameFromJWT(messageDto.getToken()));
                return new StatusP("ok", "gửi thành công", messageRepository.save(message));
            }
            message.setMessageContent(messageDto.getMessage());
            message.setUsername("Ẩn danh");
            return new StatusP("ok", "gửi thành công", messageRepository.save(message));
        }catch (Exception e){
            message.setMessageContent(messageDto.getMessage());
            message.setUsername("Ẩn danh");
            return new StatusP("ok", "gửi thành công", messageRepository.save(message));
        }
    }

    public List<MessageDto> GetMessage() {
        List<Message> message = messageRepository.get20mess();
        return message.stream().map(this::MapMessToDTO).collect(Collectors.toList());
    }

    public MessageDto MapMessToDTO(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage(message.getMessageContent());
        messageDto.setUsername(message.getUsername());
        return messageDto;
    }
}
