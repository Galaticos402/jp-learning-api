package com.self.jplearning.dto.auth;

import com.self.jplearning.entity.SystemUser;
import com.self.jplearning.utils.AppUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserRegisterDto {
    public static final String EMAIL_ATTR = "email";
    public static final String PASSWORD_ATTR = "password";
    public static final String DOB_ATTR = "birthdate";
    public static final String GENDER_ATTR = "gender";
    public static final String NICK_NAME_ATTR = "nickname";
    public static final String PHONE_NUM_ATTR = "phone_number";
    public static final String GIVEN_NAME_ATTR = "given_name";
    public static final String FAMILY_NAME_ATTR = "family_name";
    @Schema(description = "Email", example = "minh.quan040501@gmail.com", defaultValue = "minh.quan040501@gmail.com")
    private String email;
    @Schema(description = "Password", example = "wuandmSE150021@", defaultValue = "wuandmSE150021@")
    private String password;
    @Schema(description = "Birthday", example = "2001-05-04", defaultValue = "2001-05-04")
    private Date birthdate;
    @Schema(description = "Gender", example = "Male", defaultValue = "Male")
    private String gender;
    @Schema(description = "Nickname", example = "MQuan", defaultValue = "MQuan")
    private String nickName;
    @Schema(description = "Phone Numbers", example = "+84522457947", defaultValue = "+84522457947")
    private String phoneNumbers;
    @Schema(description = "Given Name", example = "Minh Quan", defaultValue = "Minh Quan")
    private String givenName;
    @Schema(description = "Family Name", example = "Dang", defaultValue = "Dang")
    private String familyName;

    public SystemUser toSystemUser(UUID userId, AppUtils.RoleType roleType){
        SystemUser systemUser = new SystemUser();
        systemUser.setUserId(userId);
        systemUser.setEmail(this.getEmail());
//        Role userRole = new Role();
//        userRole.setRoleId(roleType.getValue());
//        systemUser.setRole(userRole);
        return systemUser;
    }
}
