package com.huiaong.pikachu.common.properties;


import lombok.Data;

@Data
public class RedissonSentinelProperties {

    private String[] sentinelAddresses;

    private String masterName;

    private String password;
}
