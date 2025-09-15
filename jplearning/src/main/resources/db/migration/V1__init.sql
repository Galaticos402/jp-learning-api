CREATE DATABASE JPLearningApp;

USE JPLearningApp;


CREATE TABLE SystemUser (
  user_id UNIQUEIDENTIFIER PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
);

CREATE TABLE VocabGroup (
    group_id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    group_name VARCHAR(50) NOT NULL,
);

CREATE TABLE Vocab (
  vocab_id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
  kanji VARCHAR(50) NOT NULL,
  sino_vi VARCHAR(100) NOT NULL,
  ex_sentence VARCHAR(100) NOT NULL,
  meaning VARCHAR(100) NOT NULL,
  audio_path VARCHAR(100) NOT NULL,
  image_path VARCHAR(100) NOT NULL,
  group_id UNIQUEIDENTIFIER NOT NULL,
  CONSTRAINT fk_vocab_group
      FOREIGN KEY (group_id)
      REFERENCES VocabGroup(group_id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE RepetitionTrack (
  track_id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
  user_id UNIQUEIDENTIFIER NOT NULL,
  vocab_id UNIQUEIDENTIFIER NOT NULL,
  next_repetition_time DATE NOT NULL,
  CONSTRAINT fk_track_user
        FOREIGN KEY (user_id)
        REFERENCES SystemUser(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
  CONSTRAINT fk_track_vocab
          FOREIGN KEY (vocab_id)
          REFERENCES Vocab(vocab_id)
          ON DELETE CASCADE
          ON UPDATE CASCADE,
);

