package com.self.jplearning.repository.impl;

import com.self.jplearning.config.property.CognitoProperties;
import com.self.jplearning.dto.auth.UserRegisterDto;
import com.self.jplearning.repository.ICognitoRepository;
import com.self.jplearning.utils.AppUtils;
import com.self.jplearning.utils.CognitoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.text.SimpleDateFormat;
import java.util.Map;

public class CognitoRepositoryImpl implements ICognitoRepository {
    @Autowired
    private CognitoIdentityProviderClient cognitoClient;

    @Autowired
    private CognitoProperties cognitoProperties;

    public AdminGetUserResponse getUserByEmail(String email) {
        AdminGetUserRequest request = AdminGetUserRequest.builder()
                .userPoolId(cognitoProperties.getUserPoolId())
                .username(email)
                .build();
        return cognitoClient.adminGetUser(request);
    }

    public AdminInitiateAuthRequest login(String email, String password){
        return AdminInitiateAuthRequest.builder()
                .userPoolId(cognitoProperties.getUserPoolId())
                .clientId(cognitoProperties.getClientId())
                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH) // simple username/password auth
                .authParameters(Map.of(
                        "USERNAME", email,
                        "PASSWORD", password,
                        "SECRET_HASH", CognitoUtils.calculateSecretHash(email, cognitoProperties.getClientId(), cognitoProperties.getClientSecret())
                ))
                .build();
    }

    public boolean confirm(String email, String verificationCode){
        ConfirmSignUpRequest request =  ConfirmSignUpRequest.builder()
                .clientId(cognitoProperties.getClientId())
                .username(email)
                .confirmationCode(verificationCode)
                .secretHash(CognitoUtils.calculateSecretHash(email, cognitoProperties.getClientId(), cognitoProperties.getClientSecret()))
                .build();

        ConfirmSignUpResponse result = cognitoClient.confirmSignUp(request);

        return true;
    }
    public SignUpResponse signUp(UserRegisterDto userRegisterDto){
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .clientId(cognitoProperties.getClientId())
                .username(userRegisterDto.getEmail())
                .password(userRegisterDto.getPassword())
                .userAttributes(
                        AttributeType.builder().name(UserRegisterDto.FAMILY_NAME_ATTR).value(userRegisterDto.getFamilyName()).build(),
                        AttributeType.builder().name(UserRegisterDto.GIVEN_NAME_ATTR).value(userRegisterDto.getGivenName()).build(),
                        AttributeType.builder().name(UserRegisterDto.PHONE_NUM_ATTR).value(userRegisterDto.getPhoneNumbers()).build(),
                        AttributeType.builder().name(UserRegisterDto.DOB_ATTR).value(new SimpleDateFormat("yyyy-MM-dd").format(userRegisterDto.getBirthdate())).build(),
                        AttributeType.builder().name(UserRegisterDto.EMAIL_ATTR).value(userRegisterDto.getEmail()).build(),
                        AttributeType.builder().name(UserRegisterDto.GENDER_ATTR).value(userRegisterDto.getGender()).build(),
                        AttributeType.builder().name(UserRegisterDto.NICK_NAME_ATTR).value(userRegisterDto.getNickName()).build()
                )
                .secretHash(CognitoUtils.calculateSecretHash(userRegisterDto.getEmail(), cognitoProperties.getClientId(), cognitoProperties.getClientSecret()))
                .build();
        return cognitoClient.signUp(signUpRequest);
    }

    public ResendConfirmationCodeResponse resendConfirmationCode(String email){
        ResendConfirmationCodeRequest request = ResendConfirmationCodeRequest.builder()
                .clientId(cognitoProperties.getClientId())
                .username(email)
                .secretHash(CognitoUtils.calculateSecretHash(email, cognitoProperties.getClientId(), cognitoProperties.getClientSecret()))
                .build();
        return cognitoClient.resendConfirmationCode(request);
    }

}
