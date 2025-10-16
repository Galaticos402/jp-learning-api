package com.self.jplearning.repository.impl;

import com.self.jplearning.entity.RepetitionTrack;
import com.self.jplearning.repository.IExtendedRepetitionTrackRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

public class ExtendedRepetitionTrackRepositoryImpl implements IExtendedRepetitionTrackRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean batchUpdate(List<RepetitionTrack> repetitionTrackList, String userId) {
        int BATCH_SIZE = 50;
        for (int i = 0; i < repetitionTrackList.size(); i++) {
            RepetitionTrack trackWithAttachedUserId = repetitionTrackList.get(i);
            trackWithAttachedUserId.getUser().setUserId(UUID.fromString(userId));
            entityManager.merge(trackWithAttachedUserId);
            if (i > 0 && i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
        return true;
    }
}
