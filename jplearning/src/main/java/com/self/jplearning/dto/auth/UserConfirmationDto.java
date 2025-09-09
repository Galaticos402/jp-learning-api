package com.self.jplearning.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
public class UserConfirmationDto {
    private String email;
    private String verificationCode;
    @Data
    @Builder
    public static class ConfirmationResult{
        private String errMsg;
        private boolean status;
    }
    @Data
    @Builder
    public static class CodeResendResult{
        private String errMsg;
        private boolean status;
    }
}
