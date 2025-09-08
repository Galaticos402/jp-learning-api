package com.self.jplearning.controller;

import com.self.jplearning.dto.SignUpResponseDto;
import com.self.jplearning.dto.UserRegisterDto;
import com.self.jplearning.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<SignUpResponseDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(authService.signUpUser(userRegisterDto));
    }
}
