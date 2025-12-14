package com.self.jplearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.self.jplearning.enums.LearningStateEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "[repetition_track]")
public class RepetitionTrack {
    public static final String TRACK_ID = "track_id";
    public static final String DUE_DATE = "due_date";
    public static final String LEARNING_STATE = "learning_state";
    public static final String EASE_FACTOR = "ease_factor";
    public static final String LEARNING_STEP = "learning_step";
    public static final String LAPSE = "lapse";
    public static final String INTERVAL = "interval";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TRACK_ID, columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID trackId;

    @Column(name = DUE_DATE)
    private Date dueDate;

    @Column(name = LEARNING_STATE)
    private String learningState;

    @Column(name = EASE_FACTOR)
    private float easeFactor;

    @Column(name = LEARNING_STEP)
    private int learningStep;

//    @Column(name = LAPSE)
//    private int lapse;

    @Column(name = INTERVAL)
    private int interval;

    @ManyToOne
    @JoinColumn(name = SystemUser.USER_ID)
    private SystemUser user;

    @ManyToOne
    @JoinColumn(name = Vocab.VOCAB_ID)
    private Vocab vocab;

    public void initialize(){
        this.setLearningStep(0);
        this.setDueDate(new Date());
        this.setInterval(0);
        this.setLearningState(LearningStateEnum.NEW.getValue());
        this.setEaseFactor(2.5f);
    }

}
