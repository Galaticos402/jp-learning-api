package com.self.jplearning.service;

import com.self.jplearning.config.property.CognitoProperties;
import com.self.jplearning.dto.SignUpResponseDto;
import com.self.jplearning.dto.UserRegisterDto;
import com.self.jplearning.utils.CognitoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

import java.text.SimpleDateFormat;

@Service
public class AuthService {
    @Autowired
    private CognitoIdentityProviderClient cognitoClient;
    @Autowired
    private CognitoProperties cognitoProperties;

    public SignUpResponseDto signUpUser(UserRegisterDto userRegisterDto){
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
        SignUpResponse signUpResponse = cognitoClient.signUp(signUpRequest);
        return SignUpResponseDto.convert(signUpResponse);
    }
}
