package com.self.jplearning.dto;

import lombok.Data;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthenticationResultType;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;

    public static AuthResponseDto convert(AuthenticationResultType authResType){
        AuthResponseDto responseDto = new AuthResponseDto();
        responseDto.setAccessToken(authResType.accessToken());
        responseDto.setRefreshToken(authResType.refreshToken());
        return responseDto;
    }
}
