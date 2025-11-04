package com.self.jplearning.dto.repetition_track;

import com.self.jplearning.enums.LearningStatusEnum;
import lombok.Data;

@Data
public class RepetitionTrackProgressSyncRequest {
    private String vocabId;
    private LearningStatusEnum status;
    private int timeOfLearning;
}
