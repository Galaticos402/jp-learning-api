package com.self.jplearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "[Role]")
public class Role {
    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "role_name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ROLE_ID)
    private int roleId;

    @Column(name = ROLE_NAME)
    private String roleName;
}
