package com.self.jplearning.repository;

import com.self.jplearning.entity.VocabGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IVocabGroupRepository extends JpaRepository<VocabGroup, UUID> {
    List<VocabGroup> findByParentGroupIsNull();
    List<VocabGroup> findByParentGroup_GroupId(UUID parentGroupId);
}
