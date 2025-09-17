package com.self.jplearning.controller;

import com.self.jplearning.dto.auth.*;
import com.self.jplearning.service.AuthService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<SignUpResponseDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(authService.signUpUser(userRegisterDto));
    }
    @PostMapping(value = "/confirm")
    public ResponseEntity<UserConfirmationDto.ConfirmationResult> confirm(@RequestBody UserConfirmationDto userConfirmationDto){
        return ResponseEntity.ok(authService.confirm(userConfirmationDto.getEmail(), userConfirmationDto.getVerificationCode()));
    }
    @PostMapping(value = "/resend-code")
    public ResponseEntity<UserConfirmationDto.CodeResendResult> confirm(@RequestParam String email){
        return ResponseEntity.ok(authService.resendCode(email));
    }
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginDto userLoginDto){
        return ResponseEntity.ok(authService.login(userLoginDto.getEmail(), userLoginDto.getPassword()));
    }
}
