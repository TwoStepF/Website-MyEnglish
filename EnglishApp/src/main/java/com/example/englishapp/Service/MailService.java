package com.example.englishapp.Service;


import com.example.englishapp.Dto.StatusP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public StatusP sendMail(String email, String content) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(email);
            messageHelper.setSubject("Thư xác nhận thay đổi mật khẩu");
            messageHelper.setText(content);
        };
        try {
            mailSender.send(messagePreparator);
            return new StatusP("ok", "Mật khẩu mới đã được gửi tới email của bạn", "");
        } catch (MailException e) {
            return new StatusP("false", "Gửi email thất bại", "");
        }
    }
}
