package com.self.jplearning.controller;

import com.self.jplearning.dto.vocabgroup.VocabGroupResponseDto;
import com.self.jplearning.entity.VocabGroup;
import com.self.jplearning.mapper.VocabGroupResponseMapper;
import com.self.jplearning.service.VocabGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vocab-group")
@PreAuthorize("isAuthenticated()")
public class VocabGroupController {
    @Autowired
    private VocabGroupService vocabGroupService;
    @Autowired
    private VocabGroupResponseMapper vocabGroupResponseMapper;
    @GetMapping(value = "/root")
    public ResponseEntity<List<VocabGroupResponseDto>> getRootGroups() {
        String currentLoginUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(vocabGroupResponseMapper.toListOfVocabGroupResponseDto(vocabGroupService.getRootVocabGroups()));
    }
    @GetMapping(value = "/parent/{parentId}")
    public ResponseEntity<List<VocabGroupResponseDto>> getGroupsByParentId(@PathVariable("parentId") String parentId) {
        String currentLoginUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(vocabGroupResponseMapper.toListOfVocabGroupResponseDto(vocabGroupService.getGroupsByParentId(parentId)));
    }
}
