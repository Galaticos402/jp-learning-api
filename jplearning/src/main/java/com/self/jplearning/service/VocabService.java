package com.self.jplearning.service;

import com.self.jplearning.entity.Vocab;
import com.self.jplearning.repository.IVocabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VocabService {

    @Autowired
    private IVocabRepository vocabRepository;

    public List<Vocab> getVocabsByGroup(String groupId){
        return vocabRepository.findByVocabGroup_GroupId(UUID.fromString(groupId));
    }
}
