package com.self.jplearning.dto;

import lombok.Data;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CodeDeliveryDetailsType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

@Data
public class SignUpResponseDto {
    private boolean userConfirmed;
    private String userSub;
    private CodeDeliveryDetails codeDeliveryDetails;
    @Data
    public static class CodeDeliveryDetails {
        private String destination;
        private String deliveryMedium;
        private String attributeName;

        // Getters and setters
    }

    public static SignUpResponseDto convert(SignUpResponse signUpResult) {
        SignUpResponseDto response = new SignUpResponseDto();
        response.setUserConfirmed(signUpResult.userConfirmed());
        response.setUserSub(signUpResult.userSub());

        CodeDeliveryDetailsType awsDetails = signUpResult.codeDeliveryDetails();
        if (awsDetails != null) {
            CodeDeliveryDetails details = new CodeDeliveryDetails();
            details.setDestination(awsDetails.destination());
            details.setDeliveryMedium(awsDetails.deliveryMediumAsString());
            details.setAttributeName(awsDetails.attributeName());
            response.setCodeDeliveryDetails(details);
        }

        return response;
    }
}
