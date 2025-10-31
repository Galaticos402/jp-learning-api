package com.self.jplearning.controller;

import com.self.jplearning.dto.vocab.VocabResponseDto;
import com.self.jplearning.entity.Vocab;
import com.self.jplearning.repository.IVocabRepository;
import com.self.jplearning.service.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vocab")
@PreAuthorize("isAuthenticated()")
public class VocabController {
    @Autowired
    private VocabService vocabService;
    @GetMapping(value = "/group/{groupId}")
    public ResponseEntity<List<VocabResponseDto>> getVocabsByGroup(@PathVariable("groupId") String groupId) {
        String currentLoginUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(vocabService.findVocabsByGroupIdAndUserId(groupId, currentLoginUser));
    }
}
