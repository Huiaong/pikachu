package com.huiaong.pikachu.item.impl.cart.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.cart.constant.PikaCartConstant;
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
    private final RedisTemplate<String, Object> redisTemplate;

    private final Long DEFAULT_USER_ID = 1L;
    private final Integer DEFAULT_QUANTITY = 1;

    @Override
    public Response<Boolean> addToCart(Long goodsId) {
        redisTemplate.opsForHash().put(PikaCartConstant.CART_KEY + DEFAULT_USER_ID, "" + goodsId, DEFAULT_QUANTITY);
        return Response.ok(Boolean.TRUE);
    }

    @Override
    public Response<Boolean> incr(Long goodsId) {
        redisTemplate.opsForHash().increment(PikaCartConstant.CART_KEY + DEFAULT_USER_ID, "" + goodsId, 1);
        return Response.ok(Boolean.TRUE);
    }

    @Override
    public Response<Boolean> setQuantity(Long goodsId, Integer quantity) {
        redisTemplate.opsForHash().put(PikaCartConstant.CART_KEY + DEFAULT_USER_ID, "" + goodsId, quantity);
        return Response.ok(Boolean.TRUE);
    }
}
