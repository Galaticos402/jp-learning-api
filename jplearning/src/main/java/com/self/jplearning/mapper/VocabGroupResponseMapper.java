package com.self.jplearning.mapper;

import com.self.jplearning.dto.vocabgroup.VocabGroupResponseDto;
import com.self.jplearning.entity.VocabGroup;
import com.self.jplearning.utils.S3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class VocabGroupResponseMapper {
    @Value("${aws.s3.bucket.staticContent}")
    private String staticContentBucket;

    public VocabGroupResponseDto toVocabGroupResponseDto(VocabGroup vocabGroup){
        VocabGroupResponseDto responseDto = new VocabGroupResponseDto();
        responseDto.setGroupId(vocabGroup.getGroupId());
        responseDto.setGroupName(vocabGroup.getGroupName());
        if(!Objects.isNull(vocabGroup.getImageKey())){
            responseDto.setImageUrl(S3Utils.getPresignedUrl(staticContentBucket, vocabGroup.getImageKey()));
        }
        return responseDto;
    }

    public List<VocabGroupResponseDto> toListOfVocabGroupResponseDto(List<VocabGroup> vocabGroups){
        List<VocabGroupResponseDto> vocabGroupResponseDtos = new ArrayList<>();
        for(VocabGroup vocabGroup : vocabGroups){
            vocabGroupResponseDtos.add(toVocabGroupResponseDto(vocabGroup));
        }
        return vocabGroupResponseDtos;
    }
}
