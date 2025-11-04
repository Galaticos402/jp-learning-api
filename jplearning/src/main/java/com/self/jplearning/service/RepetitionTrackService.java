package com.self.jplearning.service;

import ch.qos.logback.core.util.StringUtil;
import com.self.jplearning.constant.AnkiConstant;
import com.self.jplearning.dto.repetition_track.RepetitionTrackProgressSyncRequest;
import com.self.jplearning.dto.repetition_track.RepetitionTrackProgressSyncResponse;
import com.self.jplearning.entity.RepetitionTrack;
import com.self.jplearning.entity.SystemUser;
import com.self.jplearning.entity.Vocab;
import com.self.jplearning.enums.LearningStateEnum;
import com.self.jplearning.enums.LearningStatusEnum;
import com.self.jplearning.repository.IExtendedRepetitionTrackRepository;
import com.self.jplearning.repository.IRepetitionTrackRepository;
import com.self.jplearning.repository.IUserRepository;
import com.self.jplearning.repository.IVocabRepository;
import com.self.jplearning.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.endpoints.internal.Value;

import java.util.*;

@Service
public class RepetitionTrackService {
    @Autowired
    private IExtendedRepetitionTrackRepository extendedRepetitionTrackRepository;

    @Autowired
    private IRepetitionTrackRepository repetitionTrackRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IVocabRepository vocabRepository;

    public RepetitionTrack findByUserIdAndVocabId(String userId, String vocabId){
        Optional<RepetitionTrack> repetitionTrackOptional = repetitionTrackRepository.findByUserUserIdAndVocabVocabId(UUID.fromString(userId), UUID.fromString(vocabId));
        return repetitionTrackOptional.orElse(null);
    }

    public boolean initLearningSession(String groupId, String userId){
        SystemUser systemUser = userRepository.findByUserId(UUID.fromString(userId)).orElse(null);
        List<Vocab> vocabList = vocabRepository.findByVocabGroup_GroupId(UUID.fromString(groupId));
        List<RepetitionTrack> repetitionTrackList = new ArrayList<>();
        try{
            if(Objects.isNull(systemUser)){
                throw new Exception("User not found");
            }
            for(Vocab vocab : vocabList){
                RepetitionTrack repetitionTrack = repetitionTrackRepository.findByUserUserIdAndVocabVocabId(UUID.fromString(userId), vocab.getVocabId()).orElse(null);
                if(Objects.isNull(repetitionTrack)){
                    // This word is not yet initialized, initialize now
                    repetitionTrack = new RepetitionTrack();
                    repetitionTrack.initialize();
                    repetitionTrack.setUser(systemUser);
                    repetitionTrack.setVocab(vocab);
                    repetitionTrackList.add(repetitionTrack);
                }
            }
            return extendedRepetitionTrackRepository.batchUpdate(repetitionTrackList);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public RepetitionTrack updateLearningProgress(String userId, String vocabId, LearningStatusEnum rating, int timeOfLearning){
        RepetitionTrack currentTrack = findByUserIdAndVocabId(userId, vocabId);
//        if(Objects.isNull(currentTrack)){
//            // This word was not initialized, initialize now
////            currentTrack = new RepetitionTrack();
////            currentTrack.set
//        }
        if (currentTrack.getLearningState().equalsIgnoreCase(LearningStateEnum.NEW.getValue()) || currentTrack.getLearningState().equalsIgnoreCase(LearningStateEnum.LEARNING.getValue())){
            if (rating.getValue() == LearningStatusEnum.AGAIN.getValue()){
                currentTrack.setLearningStep(0);
                currentTrack.setLearningState(LearningStateEnum.LEARNING.getValue());
            } else if (rating.getValue() == LearningStatusEnum.GOOD.getValue()) {
                currentTrack.setLearningState(LearningStateEnum.REVIEW.getValue());
                currentTrack.setInterval(1);
                currentTrack.setDueDate(DateUtils.add(new Date(), 1, DateUtils.DAY));
                currentTrack.setEaseFactor(2.5f);
                currentTrack.setLearningStep(0);
            } else{
                currentTrack.setLearningStep(currentTrack.getLearningStep() + 1);
                if(currentTrack.getLearningStep() > 1 || timeOfLearning > 1){
                    currentTrack.setLearningState(LearningStateEnum.REVIEW.getValue());
                    currentTrack.setInterval(1);
                    currentTrack.setDueDate(DateUtils.add(new Date(), 1, DateUtils.DAY));
                    currentTrack.setEaseFactor(2.5f);
                    currentTrack.setLearningStep(0);
                }else{
                    currentTrack.setLearningState(LearningStateEnum.LEARNING.getValue());
                }
            }
        } else if (currentTrack.getLearningState().equalsIgnoreCase(LearningStateEnum.REVIEW.getValue())) {
            if(rating.getValue() == LearningStatusEnum.AGAIN.getValue()){
                int interval = Math.max(1, (int)(currentTrack.getInterval() * AnkiConstant.LAPSE_MULTIPLIER));
                float easeFactor = Math.max(currentTrack.getEaseFactor() - AnkiConstant.LAPSE_EASE_DECREMENT, AnkiConstant.MIN_EASE);
                currentTrack.setDueDate(DateUtils.add(new Date(), interval, DateUtils.DAY));
                currentTrack.setInterval(interval);
                currentTrack.setEaseFactor(easeFactor);
                currentTrack.setLearningState(LearningStateEnum.RELEARNING.getValue());
                currentTrack.setLearningStep(0);
            } else if (rating.getValue() == LearningStatusEnum.HARD.getValue()) {
                int interval = Math.max(1, (int)(currentTrack.getInterval() * AnkiConstant.HARD_MULTIPLIER));
                float easeFactor = Math.max(currentTrack.getEaseFactor() - AnkiConstant.HARD_EASE_DECREMENT, AnkiConstant.MIN_EASE);
                currentTrack.setInterval(interval);
                currentTrack.setEaseFactor(easeFactor);
                currentTrack.setDueDate(DateUtils.add(new Date(), interval, DateUtils.DAY));
            } else if (rating.getValue() == LearningStatusEnum.GOOD.getValue()) {
                int interval = Math.max(1, (int)(currentTrack.getInterval() * AnkiConstant.DEFAULT_EASE_FACTOR));
                currentTrack.setInterval(interval);
                currentTrack.setDueDate(DateUtils.add(new Date(), interval, DateUtils.DAY));
            } else if (rating.getValue() == LearningStatusEnum.EASY.getValue()) {
                int interval = Math.max(1, (int)(currentTrack.getInterval() * currentTrack.getEaseFactor() * AnkiConstant.EASE_BONUS));
                float easeFactor = currentTrack.getEaseFactor() + AnkiConstant.EASY_EASE_INCREMENT;
                currentTrack.setInterval(interval);
                currentTrack.setEaseFactor(easeFactor);
                currentTrack.setDueDate(DateUtils.add(new Date(), interval, DateUtils.DAY));
            }
        } else if (currentTrack.getLearningState().equalsIgnoreCase(LearningStateEnum.RELEARNING.getValue())) {
            if(rating.getValue() == LearningStatusEnum.AGAIN.getValue()){
                currentTrack.setLearningStep(0);
            }else{
                int learningStep = currentTrack.getLearningStep() + 1;
                currentTrack.setLearningStep(learningStep);
                if (learningStep > 1){
                    currentTrack.setLearningState(LearningStateEnum.REVIEW.getValue());
                    int interval = 1;
                    currentTrack.setInterval(interval);
                    currentTrack.setDueDate(DateUtils.add(new Date(), interval, DateUtils.DAY));
                }
            }
        }
        return currentTrack;
    }

    public RepetitionTrackProgressSyncResponse syncProgress(List<RepetitionTrackProgressSyncRequest> syncRequestList, String userId) {
        List<RepetitionTrack> repetitionTrackList = new ArrayList<>();
        for(RepetitionTrackProgressSyncRequest syncRequest : syncRequestList){
            repetitionTrackList.add(updateLearningProgress(userId, syncRequest.getVocabId(), syncRequest.getStatus(), syncRequest.getTimeOfLearning()));
        }
        RepetitionTrackProgressSyncResponse syncResponse = new RepetitionTrackProgressSyncResponse();
        syncResponse.setStatus(extendedRepetitionTrackRepository.batchUpdate(repetitionTrackList));
        syncResponse.setErrMsg(StringUtils.EMPTY);

        return syncResponse;
    }
}
