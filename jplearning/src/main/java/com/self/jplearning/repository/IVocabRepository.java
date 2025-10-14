package com.self.jplearning.repository;

import com.self.jplearning.entity.Vocab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IVocabRepository extends JpaRepository<Vocab, UUID> {
    List<Vocab> findByVocabGroup_GroupId(UUID groupId);
}
