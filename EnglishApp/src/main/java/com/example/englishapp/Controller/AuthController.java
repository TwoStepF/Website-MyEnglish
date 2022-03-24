package com.example.englishapp.Controller;

import com.example.englishapp.Dto.*;
import com.example.englishapp.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    private StatusP SignIn(@RequestBody SigninRequest signinRequest){

        return authService.SignIn(signinRequest);
    }

    @PostMapping("/login")
    private StatusLogin Login(@RequestBody SigninRequest signinRequest){

        return authService.Login(signinRequest);
    }

    @PostMapping("/password/forgot")
    private StatusP findUserByEmail(@RequestBody emailDto Email){
        return authService.findUserByEmail(Email);
    }

    @GetMapping("/accountVerification/{token}")
    private StatusP ActivePassword(@PathVariable String token){
        return authService.activePassword(token);
    }

    @PutMapping("/password/change")
    private StatusP ChangePassword(@RequestBody ChangePassDto changePassDto){
        return authService.changePassword(changePassDto);
    }

    @PostMapping("/logout")
    private StatusP Logout(@RequestBody SigninRequest signinRequest){
        return authService.logout(signinRequest);
    }
}
