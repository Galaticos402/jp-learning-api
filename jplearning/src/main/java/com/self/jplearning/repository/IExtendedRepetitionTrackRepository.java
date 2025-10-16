package com.self.jplearning.repository;

import com.self.jplearning.entity.RepetitionTrack;

import java.util.List;

public interface IExtendedRepetitionTrackRepository {
    boolean batchUpdate(List<RepetitionTrack> repetitionTrackList, String userId);
}
