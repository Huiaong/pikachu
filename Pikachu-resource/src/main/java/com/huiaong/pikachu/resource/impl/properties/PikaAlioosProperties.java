package com.huiaong.pikachu.resource.impl.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "alioos")
public class PikaAlioosProperties {

    private String endpoint;

    private String accessKey;

    private String accessKeySecret;

    private String bucketName;
}
