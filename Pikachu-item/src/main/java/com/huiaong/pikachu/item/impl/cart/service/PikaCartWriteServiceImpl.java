package com.huiaong.pikachu.item.impl.cart.service;

import com.huiaong.pikachu.item.cart.service.PikaCartWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service
public class PikaCartWriteServiceImpl implements PikaCartWriteService {
    private final RedisTemplate<String, Object> pikaCartDao;
}
