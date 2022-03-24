package com.example.englishapp.Service;

import com.example.englishapp.Dto.*;
import com.example.englishapp.Entity.User;
import com.example.englishapp.Entity.VerificationToken;
import com.example.englishapp.Repository.TokeninforRepository;
import com.example.englishapp.Repository.UserRepository;
import com.example.englishapp.Repository.VerificationTokenRepository;
import com.example.englishapp.Security.jwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.Instant;

import java.util.UUID;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private TokeninforRepository tokeninforRepository;

    @Autowired
    private jwtToken jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Authentication authenticate;
    public StatusP SignIn(SigninRequest signinRequest) {
        try {
            User user = new User(signinRequest.getUsername(), passwordEncoder.encode(signinRequest.getPassword()), signinRequest.getEmail());
            userRepository.save(user);
            return new StatusP("ok", "thành công", "");
        }catch (Exception e){
            return new StatusP("false", "Tên tài khoản hoặc email đã bị trùng", "");
        }
    }

    @Transactional
    public StatusLogin Login(SigninRequest signinRequest) {
        try {
            User user = userRepository.findByUsername(signinRequest.getUsername());
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return new StatusLogin( "ok", "Đăng nhập thành công", jwtToken.generateToken(user.getUsername()), user.getUsername(), user.getRole());
        }catch (Exception e){
            return new StatusLogin("false", "Tài khoản hoặc mật khẩu không chính xác", null, null, null);
        }
    }
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername());
    }

    public StatusP findUserByEmail(emailDto email) {
        User user = userRepository.findByEmail(email.getEmail()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        if(user == null) {
            return new StatusP("false", "Không tìm thấy người dùng", "");
        }else{
            VerificationToken token = generateVerificationToken(user);
            return  mailService.sendMail(user.getEmail(), "Xin chào! Mật khẩu mới của bạn là: " +
                    token.getPassword() + '\n' + "Lưu ý, đổi mật khẩu để đảm bảo an toàn tuyệt đối" +
                    '\n' + "Click vào đây để kich hoạt tài khoản mới: " +
                    "http://localhost:8082/api/auth/accountVerification/" + token.getToken());
        }
    }

    private String generatePassword() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++)
            sb.append(chars.charAt(random.nextInt(chars.length())));
        return sb.toString();
    }

    private VerificationToken generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        String Pass = generatePassword();
        try {
            VerificationToken find = verificationTokenRepository.findByUserID(user.getUserID());
            find.setToken(token);
            find.setUser(user);
            find.setExpiryDate(Instant.now());
            find.setPassword(Pass);
            verificationTokenRepository.save(find);
            return find;
        }catch (Exception e){
            VerificationToken find = new VerificationToken();
            find.setToken(token);
            find.setUser(user);
            find.setExpiryDate(Instant.now());
            find.setPassword(Pass);
            verificationTokenRepository.save(find);
            return find;
        }
    }
    @Transactional
    public StatusP activePassword(String token) {
        try {
            VerificationToken verificationToken = verificationTokenRepository.findbyToken(token);
            User user = userRepository.findByUserID(verificationToken.getId());
            user.setPassword(passwordEncoder.encode(verificationToken.getPassword()));
            userRepository.save(user);
            verificationToken.setToken(null);
            verificationToken.setPassword(null);
            verificationTokenRepository.save(verificationToken);
            return new StatusP("ok", "Kích hoạt mật khẩu thành công", "");
        }catch (Exception e){
            return new StatusP("false", "Kích hoạt thất bại! Đường dẫn hết hạn hoặc tài khoản không tồn tại", "");
        }
    }

    public StatusP changePassword(ChangePassDto changePassDto) {
        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(changePassDto.getUsername(), changePassDto.getPassword())
            );
            User user = userRepository.findByUsername(changePassDto.getUsername());
            user.setPassword(passwordEncoder.encode(changePassDto.getNewpass()));
            userRepository.save(user);
            return new StatusP("ok", "Doi mat khau thanh cong", "");
        }catch (Exception e){
            return new StatusP("false", "Tai khoan hoac mat khau khong chinh xac", "");
        }
    }

    @Transactional
    public StatusP logout(SigninRequest signinRequest) {
        tokeninforRepository.deleteByUsername(signinRequest.getUsername());
        return new StatusP("ok", "Đăng xuất thành công", "");
    }
}
