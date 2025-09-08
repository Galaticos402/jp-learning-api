package com.self.jplearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "[SystemUser]")
public class SystemUser {
    public static final String USER_ID = "user_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String NICK_NAME = "nick_name";
    public static final String EMAIL = "email";
    public static final String PHONE_NUM = "phone_number";
    public static final String DOB = "dob";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = USER_ID)
    private UUID userId;

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = NICK_NAME)
    private String nickName;

    @Column(name = EMAIL)
    private String email;

    @Column(name = PHONE_NUM)
    private String phoneNum;

    @Column(name = DOB)
    private Date dob;

    @ManyToOne
    @JoinColumn(name = Role.ROLE_ID)
    private Role role;
}
