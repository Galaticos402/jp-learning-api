package com.self.jplearning.dto.auth;

import lombok.Data;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthenticationResultType;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private boolean needConfirmation = false;
    private String errMsg;

    public static AuthResponseDto success(AuthenticationResultType authResType){
        AuthResponseDto responseDto = new AuthResponseDto();
        responseDto.setAccessToken(authResType.accessToken());
        responseDto.setRefreshToken(authResType.refreshToken());
        return responseDto;
    }

    public static AuthResponseDto requireConfirmation(){
        AuthResponseDto responseDto = new AuthResponseDto();
        responseDto.setAccessToken(null);
        responseDto.setRefreshToken(null);
        responseDto.setNeedConfirmation(true);
        responseDto.setErrMsg(null);
        return responseDto;
    }

    public static AuthResponseDto failed(String msg){
        AuthResponseDto responseDto = new AuthResponseDto();
        responseDto.setAccessToken(null);
        responseDto.setRefreshToken(null);
        responseDto.setNeedConfirmation(false);
        responseDto.setErrMsg(msg);
        return responseDto;
    }


}
