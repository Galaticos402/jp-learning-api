package com.self.jplearning.dto.vocabgroup;

import lombok.Data;

import java.util.UUID;
@Data
public class VocabGroupResponseDto {
    private UUID groupId;
    private String groupName;
    private String imageUrl;
    private boolean isLeaf;
}
