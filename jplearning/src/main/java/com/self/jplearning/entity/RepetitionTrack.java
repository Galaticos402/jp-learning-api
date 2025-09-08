package com.self.jplearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "[RepetitionTrack]")
public class RepetitionTrack {
    public static final String TRACK_ID = "track_id";
    public static final String NEXT_REPETITION_TIME = "next_repetition_time";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TRACK_ID)
    private UUID trackId;

    @ManyToOne
    @JoinColumn(name = SystemUser.USER_ID)
    private SystemUser user;

    @ManyToOne
    @JoinColumn(name = Vocab.VOCAB_ID)
    private Vocab vocab;

    @Column(name = NEXT_REPETITION_TIME)
    private Date nextRepetitionTime;
}
