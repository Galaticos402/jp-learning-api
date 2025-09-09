package com.self.jplearning.repository;


import com.self.jplearning.dto.auth.UserRegisterDto;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ResendConfirmationCodeResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

public interface ICognitoRepository {
    AdminGetUserResponse getUserByEmail(String email);
    AdminInitiateAuthRequest login(String email, String password);
    boolean confirm(String email, String verificationCode);
    SignUpResponse signUp(UserRegisterDto userRegisterDto);
    ResendConfirmationCodeResponse resendConfirmationCode(String email);
}
