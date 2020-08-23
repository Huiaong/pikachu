package com.huiaong.pikachu.item.impl.config;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.huiaong.pikachu.common.properties.RedissonClusterProperties;
import com.huiaong.pikachu.common.properties.RedissonProperties;
import com.huiaong.pikachu.common.properties.RedissonSentinelProperties;
import com.huiaong.pikachu.common.properties.RedissonSingleProperties;
import lombok.AllArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@AllArgsConstructor
@EnableConfigurationProperties(RedissonProperties.class)
public class PikaItemRedissonConfig {
    private final RedissonProperties redissonProperties;

    /**
     * 哨兵模式自动装配
     *
     * @return RedissonClient
     */
    @Bean
    @ConditionalOnProperty(name = "redisson.mode", havingValue = "sentinel")
    RedissonClient redissonSentinel() {
        Config config = new Config();
        RedissonSentinelProperties sentinelProperties = redissonProperties.getSentinel();
        String[] sentinelAddresses = sentinelProperties.getSentinelAddresses();
        Arrays.stream(sentinelAddresses).forEach(address -> address = address.startsWith("redis://") ? address : "redis://" + address);


        SentinelServersConfig serverConfig = config.useSentinelServers()
                .addSentinelAddress(sentinelAddresses)
                .setMasterName(sentinelProperties.getMasterName());
        if (StringUtils.isNotEmpty(sentinelProperties.getPassword())) {
            serverConfig.setPassword(sentinelProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 单机模式 redisson 客户端
     *
     * @return RedissonClient
     */
    @Bean
    @ConditionalOnProperty(name = "redisson.mode", havingValue = "single")
    RedissonClient redissonSingle() {
        Config config = new Config();
        RedissonSingleProperties singleProperties = redissonProperties.getSingle();

        String node = singleProperties.getAddress();
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node);
        if (StringUtils.isNotEmpty(singleProperties.getPassword())) {
            serverConfig.setPassword(singleProperties.getPassword());
        }
        return Redisson.create(config);
    }


    /**
     * 集群模式的 redisson 客户端
     *
     * @return RedissonClient
     */
    @Bean
    @ConditionalOnProperty(name = "redisson.mode", havingValue = "cluster")
    RedissonClient redissonCluster() {
        Config config = new Config();
        RedissonClusterProperties clusterProperties = redissonProperties.getCluster();

        String[] clusterAddress = clusterProperties.getClusterAddress();
        Arrays.stream(clusterAddress).forEach(address -> address = address.startsWith("redis://") ? address : "redis://" + address);

        ClusterServersConfig serverConfig = config.useClusterServers()
                .addNodeAddress(clusterAddress)
                .setScanInterval(clusterProperties.getScanInterval());
        if (StringUtils.isNotEmpty(clusterProperties.getPassword())) {
            serverConfig.setPassword(clusterProperties.getPassword());
        }
        return Redisson.create(config);
    }

}
