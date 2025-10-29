package com.self.jplearning.controller;

import com.self.jplearning.entity.RepetitionTrack;
import com.self.jplearning.entity.Vocab;
import com.self.jplearning.service.RepetitionTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracker")
@PreAuthorize("isAuthenticated()")
public class RepetitionTrackController {
    @Autowired
    private RepetitionTrackService repetitionTrackService;

    @PostMapping(value = "/batch-update")
    public ResponseEntity<Boolean> trackBatchUpdate(@RequestBody List<RepetitionTrack> repetitionTrackList) {
        String currentLoginUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(repetitionTrackService.batchUpdate(repetitionTrackList, currentLoginUser));
    }
}
