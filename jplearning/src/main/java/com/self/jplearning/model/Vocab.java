package com.self.jplearning.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "[Vocab]")
public class Vocab {
    public static final String VOCAB_ID = "vocab_id";
    public static final String KANJI = "kanji";
    public static final String SINO_V = "sino_v";
    public static final String EX_SENTENCE = "ex_sentence";
    public static final String MEANING = "meaning";
    public static final String AUDIO_PATH = "audio_path";
    public static final String IMAGE_PATH = "image_path";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = VOCAB_ID)
    private UUID vocabId;

    @Column(name = KANJI)
    private String kanji;

    @Column(name = SINO_V)
    private String sinoV;

    @Column(name = EX_SENTENCE)
    private String exSentence;

    @Column(name = MEANING)
    private String meaning;

    @Column(name = AUDIO_PATH)
    private String audioPath;

    @Column(name = IMAGE_PATH)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = VocabGroup.GROUP_ID)
    private VocabGroup vocabGroup;
}
