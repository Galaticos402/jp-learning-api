package com.self.jplearning.utils;

public class QueryUtils {
    public static final String QUERY_VOCABS_PER_LEARNING = """
             select
             new com.self.jplearning.dto.vocab.VocabResponseDto (v,\s
              CASE
                   WHEN v.vocabId not in ( \s
                       select v.vocabId from Vocab v inner join RepetitionTrack rptr on v.vocabId = rptr.vocab.vocabId where v.vocabGroup.groupId = :group_id and rptr.user.userId = :user_id
                   )
                   THEN 'NEW'
                   WHEN v.vocabId in ( \s
                       select v.vocabId from Vocab v inner join RepetitionTrack rptr on v.vocabId = rptr.vocab.vocabId where v.vocabGroup.groupId = :group_id and rptr.user.userId = :user_id and CURRENT_TIMESTAMP > rptr.dueDate
                   )
                   THEN 'DUE'
                   ELSE 'NOT DUE'
               END AS cluster) from Vocab v where v.vocabGroup.groupId = :group_id
               and (
                    CASE
                   WHEN v.vocabId not in ( \s
                       select v.vocabId from Vocab v inner join RepetitionTrack rptr on v.vocabId = rptr.vocab.vocabId where v.vocabGroup.groupId = :group_id and rptr.user.userId = :user_id
                   )
                   THEN 'NEW'
                   WHEN v.vocabId in ( \s
                       select v.vocabId from Vocab v inner join RepetitionTrack rptr on v.vocabId = rptr.vocab.vocabId where v.vocabGroup.groupId = :group_id and rptr.user.userId = :user_id and CURRENT_TIMESTAMP > rptr.dueDate
                   )
                   THEN 'DUE'
                   ELSE 'NOT DUE'
               END
               ) <> 'DUE'
            """;
}
