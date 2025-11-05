package com.self.jplearning.repository;

import com.self.jplearning.dto.vocab.VocabResponseDto;
import com.self.jplearning.entity.Vocab;
import com.self.jplearning.utils.QueryUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IVocabRepository extends JpaRepository<Vocab, UUID> {
    List<Vocab> findByVocabGroup_GroupId(UUID groupId);

    Optional<Vocab> findByVocabId(UUID vocabId);

    @Query(QueryUtils.QUERY_DUE_VOCABS)
    List<VocabResponseDto> findDueVocabsByGroupIdAndUserId(@Param("group_id") UUID groupId, @Param("user_id") UUID userId);
}
