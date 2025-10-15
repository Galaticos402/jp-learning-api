package com.self.jplearning.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RepetitionTrackService {
    public Date getNextRepetitionDate(String vocabId){
        return new Date();
    }
}
