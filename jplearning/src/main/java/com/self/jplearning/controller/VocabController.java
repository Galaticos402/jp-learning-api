package com.self.jplearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vocab")
public class VocabController {
    @GetMapping(value = "/list")
    public ResponseEntity<String> getVocabList() {
        String currentLoginUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(String.format("Get vocab list called by %s", currentLoginUser));
    }
}
