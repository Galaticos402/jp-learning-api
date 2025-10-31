package com.self.jplearning.dto.vocab;

import com.self.jplearning.entity.Vocab;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class VocabResponseDto {
    private Vocab vocab;
    private String cluster;
}
