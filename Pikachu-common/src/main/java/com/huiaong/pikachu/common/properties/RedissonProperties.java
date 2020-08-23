package com.huiaong.pikachu.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {

    private RedissonSingleProperties single;

    private RedissonSentinelProperties sentinel;

    private RedissonClusterProperties cluster;

}
