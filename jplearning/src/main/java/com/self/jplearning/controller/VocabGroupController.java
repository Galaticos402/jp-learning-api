package com.self.jplearning.controller;

import com.self.jplearning.entity.VocabGroup;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vocab-group")
public class VocabGroupController {
    @GetMapping(value = "/root")
    public ResponseEntity<List<VocabGroup>> getRootGroups() {
        String currentLoginUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(new ArrayList<>());
    }
}
