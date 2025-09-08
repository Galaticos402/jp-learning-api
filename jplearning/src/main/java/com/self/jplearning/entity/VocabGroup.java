package com.self.jplearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "[VocabGroup]")
public class VocabGroup {
    public static final String GROUP_ID = "group_id";
    public static final String GROUP_NAME = "group_name";
    public static final String PARENT_GROUP_ID = "parent_group_id";
    public static final String PARENT_GROUP = "parentGroup";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = GROUP_ID)
    private UUID groupId;

    @Column(name = GROUP_NAME)
    private String groupName;

    @ManyToOne
    @JoinColumn(name = VocabGroup.PARENT_GROUP_ID)
    private VocabGroup parentGroup;

    @OneToMany(mappedBy = PARENT_GROUP)
    private List<VocabGroup> childGroups;
}
