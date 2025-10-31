package com.self.jplearning.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    public static final String IMAGE_KEY = "image_key";
    public static final String PARENT_GROUP_ID = "parent_group_id";
    public static final String ORD = "ord";
    public static final String IS_LEAF = "is_leaf";
    public static final String PARENT_GROUP = "parentGroup";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = GROUP_ID)
    private UUID groupId;

    @Column(name = GROUP_NAME)
    private String groupName;

    @Column(name = IMAGE_KEY)
    private String imageKey;

    @Column(name = ORD)
    private String ord;

    @Column(name = IS_LEAF)
    private boolean isLeaf;

    @ManyToOne
    @JoinColumn(name = VocabGroup.PARENT_GROUP_ID)
    @JsonBackReference
    @JsonIgnore
    private VocabGroup parentGroup;

    @OneToMany(mappedBy = PARENT_GROUP)
    @JsonManagedReference
    @JsonIgnore
    private List<VocabGroup> childGroups;
}
