package com.self.jplearning.service;

import com.self.jplearning.entity.VocabGroup;
import com.self.jplearning.repository.IVocabGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VocabGroupService {
    @Autowired
    private IVocabGroupRepository vocabGroupRepository;

    public List<VocabGroup> getRootVocabGroups(){
        return vocabGroupRepository.findByParentGroupIsNull();
    }

    public List<VocabGroup> getGroupsByParentId(String parentId){
            return vocabGroupRepository.findByParentGroup_GroupId(UUID.fromString(parentId));
    }
}
