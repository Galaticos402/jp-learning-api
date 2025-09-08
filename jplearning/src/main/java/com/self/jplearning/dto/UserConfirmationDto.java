package com.self.jplearning.dto;

import lombok.Data;

@Data
public class UserConfirmationDto {
    private String email;
    private String verificationCode;
}
