package com.self.jplearning.service;

import com.self.jplearning.config.property.CognitoProperties;
import com.self.jplearning.dto.auth.AuthResponseDto;
import com.self.jplearning.dto.auth.SignUpResponseDto;
import com.self.jplearning.dto.auth.UserConfirmationDto;
import com.self.jplearning.dto.auth.UserRegisterDto;
import com.self.jplearning.entity.SystemUser;
import com.self.jplearning.repository.ICognitoRepository;
import com.self.jplearning.repository.IUserRepository;
import com.self.jplearning.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class AuthService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private CognitoIdentityProviderClient cognitoClient;
    @Autowired
    private CognitoProperties cognitoProperties;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICognitoRepository cognitoRepository;

    public SignUpResponseDto signUpUser(UserRegisterDto userRegisterDto){
        // Sign up with Cognito
        SignUpResponse signUpResponse = cognitoRepository.signUp(userRegisterDto);
        // Sign up complete, now store user references in database
        UUID userId = UUID.fromString(signUpResponse.userSub());
        SystemUser systemUser = userRegisterDto.toSystemUser(userId, AppUtils.RoleType.LEARNER);
        userRepository.save(systemUser);

        return SignUpResponseDto.convert(signUpResponse);
    }

    public UserConfirmationDto.ConfirmationResult confirm(String email, String verificationCode){
        try{
            boolean confirmResult = cognitoRepository.confirm(email, verificationCode);
            return UserConfirmationDto.ConfirmationResult.builder().status(confirmResult).build();
        }
        catch (ExpiredCodeException expiredCodeException){
            return UserConfirmationDto.ConfirmationResult.builder().errMsg("Token expired").build();
        }
        catch (CodeMismatchException mismatchException){
            return UserConfirmationDto.ConfirmationResult.builder().errMsg("Token mismatched").build();
        }
        catch (Exception e){
            return UserConfirmationDto.ConfirmationResult.builder().errMsg(e.getMessage()).build();
        }
    }

    public UserConfirmationDto.CodeResendResult resendCode(String email){
        try{
            ResendConfirmationCodeResponse res = cognitoRepository.resendConfirmationCode(email);
            return UserConfirmationDto.CodeResendResult.builder().status(true).build();
        }catch (Exception e){
            logger.info(e.getMessage());
            return UserConfirmationDto.CodeResendResult.builder()
                    .status(false)
                    .errMsg(e.getMessage())
                    .build();
        }
    }

    public AuthResponseDto login(String email, String password){
        // Check if user has existed on Cognito
        try{
            AdminGetUserResponse userOnCognito = cognitoRepository.getUserByEmail(email);
            boolean isEmailVerified = false;

            for (AttributeType attribute : userOnCognito.userAttributes()) {
                if ("email_verified".equals(attribute.name())) {
                    isEmailVerified = Boolean.parseBoolean(attribute.value());
                    break;
                }
            }
            if(!isEmailVerified){
                // User has existed yet has not been verified - notify the client
                // Publish a new code for verification
                resendCode(email);
                return AuthResponseDto.requireConfirmation();
            }
            // User existed and already enabled - Proceed with tokens
            AdminInitiateAuthRequest authRequest = cognitoRepository.login(email, password);
            AdminInitiateAuthResponse response = cognitoClient.adminInitiateAuth(authRequest);
            return AuthResponseDto.success(response.authenticationResult());
        }
        catch (CognitoIdentityProviderException e){
            return AuthResponseDto.failed(e.awsErrorDetails().errorMessage());
        }
    }
}
