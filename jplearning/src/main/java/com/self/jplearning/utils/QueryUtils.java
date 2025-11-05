package com.self.jplearning.utils;

public class QueryUtils {
    public static final String QUERY_DUE_VOCABS = """
             select
             new com.self.jplearning.dto.vocab.VocabResponseDto (v,\s
              rpt.learningState as learningState, rpt.learningStep as learningStep
              ) from Vocab v inner join RepetitionTrack rpt on v.vocabId = rpt.vocab.vocabId and rpt.user.userId = :user_id and v.vocabGroup.groupId = :group_id
              where rpt.dueDate < CURRENT_TIMESTAMP
            """;
}
