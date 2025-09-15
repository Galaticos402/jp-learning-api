package com.self.jplearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "SystemUser", schema = "dbo")
public class SystemUser {
    public static final String USER_ID = "user_id";
    public static final String EMAIL = "email";

    @Id
    @Column(name = USER_ID)
    private UUID userId;

    @Column(name = EMAIL)
    private String email;
}
