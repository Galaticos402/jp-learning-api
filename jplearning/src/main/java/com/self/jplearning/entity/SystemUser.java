package com.self.jplearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "[system_user]")
public class SystemUser {
    public static final String USER_ID = "user_id";
    public static final String EMAIL = "email";

    @Id
    @Column(name = USER_ID, columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = EMAIL, length = 50)
    private String email;
}
