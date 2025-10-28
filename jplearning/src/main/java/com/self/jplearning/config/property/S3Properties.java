package com.self.jplearning.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
@Data
public class S3Properties {
    @Data
    private class Bucket{
        private String staticContent;
    }

    private String region;
    private Bucket bucket;
}
