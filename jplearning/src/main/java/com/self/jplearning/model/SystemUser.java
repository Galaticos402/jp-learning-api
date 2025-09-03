package com.self.jplearning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
public class SystemUser {
    private static final String USER_ID = "user_id";
    private static final String FIRST_NAME = "first_name";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = USER_ID)
    private UUID userId;

    @Column(name = FIRST_NAME)
    private String firstName;
}
