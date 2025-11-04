ALTER TABLE RepetitionTrack
ADD interval int NOT NULL DEFAULT 0;

ALTER TABLE RepetitionTrack
DROP COLUMN lapse;