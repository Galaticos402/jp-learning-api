package com.self.jplearning.service;

import com.self.jplearning.entity.RepetitionTrack;
import com.self.jplearning.enums.LearningStatusEnum;
import com.self.jplearning.repository.IExtendedRepetitionTrackRepository;
import com.self.jplearning.repository.IRepetitionTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RepetitionTrackService {
    @Autowired
    private IExtendedRepetitionTrackRepository extendedRepetitionTrackRepository;

    public boolean batchUpdate(List<RepetitionTrack> repetitionTrackList, String userId){
        return extendedRepetitionTrackRepository.batchUpdate(repetitionTrackList, userId);
    }
}
