package com.self.jplearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "[vocab]")
public class Vocab {
    public static final String VOCAB_ID = "vocab_id";
    public static final String KANJI = "kanji";
    public static final String FURIGANA = "furigana";
    public static final String SINO_V = "sino_vi";
    public static final String EX_SENTENCE = "ex_sentence";
    public static final String EX_SENTENCE_MEANING = "ex_sentence_meaning";
    public static final String MEANING = "meaning";
    public static final String AUDIO_PATH = "audio_path";
    public static final String IMAGE_PATH = "image_path";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = VOCAB_ID, columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID vocabId;

    @Column(name = KANJI, length = 50)
    private String kanji;

    @Column(name = FURIGANA, length = 50)
    private String furigana;

    @Column(name = SINO_V, length = 50)
    private String sinoV;

    @Column(name = EX_SENTENCE, columnDefinition = "text")
    private String exSentence;

    @Column(name = EX_SENTENCE_MEANING, columnDefinition = "text")
    private String exSentenceMeaning;

    @Column(name = MEANING, columnDefinition = "text")
    private String meaning;

    @Column(name = AUDIO_PATH, length = 100)
    private String audioPath;

    @Column(name = IMAGE_PATH, length = 100)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = VocabGroup.GROUP_ID)
    private VocabGroup vocabGroup;
}
