package com.huiaong.pikachu.common.properties;

import lombok.Data;
import org.redisson.executor.RedissonClassLoader;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {

    private RedissonSingleProperties single;

    private RedissonSentinelProperties sentinel;

    private RedissonClusterProperties cluster;

}
