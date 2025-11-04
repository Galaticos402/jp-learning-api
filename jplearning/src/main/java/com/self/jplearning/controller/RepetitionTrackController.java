package com.self.jplearning.controller;

import com.self.jplearning.dto.repetition_track.RepetitionTrackProgressSyncRequest;
import com.self.jplearning.dto.repetition_track.RepetitionTrackProgressSyncResponse;
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

    @PostMapping(value = "/session/start/{groupId}")
    public ResponseEntity<Boolean> initSession(@PathVariable("groupId") String groupId){
        String currentLoginUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(repetitionTrackService.initLearningSession(groupId, currentLoginUser));
    }


    @PostMapping(value = "/progress/sync")
    public ResponseEntity<RepetitionTrackProgressSyncResponse> syncProgress(@RequestBody List<RepetitionTrackProgressSyncRequest> syncReqList){
        String currentLoginUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(repetitionTrackService.syncProgress(syncReqList, currentLoginUser));
    }
}
