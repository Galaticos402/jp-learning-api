ALTER TABLE VocabGroup
ADD parent_group_id UNIQUEIDENTIFIER NULL;

ALTER TABLE VocabGroup
ADD CONSTRAINT fk_group_group
FOREIGN KEY (parent_group_id) REFERENCES VocabGroup(group_id);