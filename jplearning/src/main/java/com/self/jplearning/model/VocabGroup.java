package com.self.jplearning.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "[VocabGroup]")
public class VocabGroup {
    public static final String GROUP_ID = "group_id";
    public static final String GROUP_NAME = "group_name";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = GROUP_ID)
    private UUID groupId;

    @Column(name = GROUP_NAME)
    private String groupName;
}
