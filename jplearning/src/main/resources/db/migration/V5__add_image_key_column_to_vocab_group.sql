ALTER TABLE VocabGroup
ADD image_key NVARCHAR(255);

ALTER TABLE VocabGroup
ADD ord int;

ALTER TABLE VocabGroup
ADD is_leaf bit NOT NULL Default 0;