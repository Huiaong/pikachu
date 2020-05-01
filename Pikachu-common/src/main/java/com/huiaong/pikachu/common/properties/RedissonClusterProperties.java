package com.huiaong.pikachu.common.properties;

import lombok.Data;

@Data
public class RedissonClusterProperties {
    private String[] clusterAddress;

    private Integer scanInterval;

    private String password;
}
