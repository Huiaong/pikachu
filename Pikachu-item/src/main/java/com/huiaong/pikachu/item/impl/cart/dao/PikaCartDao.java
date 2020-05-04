package com.huiaong.pikachu.item.impl.cart.dao;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huiaong.pikachu.item.cart.constant.PikaCartConstant;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PikaCartDao {
    private final RedissonClient redissonClient;
    private final RedisTemplate<String, String> redisTemplate;

    public Boolean addToCart(Long userId, Long goodsId) {
        redisTemplate.opsForHash().increment(PikaCartConstant.CART_KEY + userId, goodsId, 1L);
        return Boolean.TRUE;
    }

    public Boolean incr(Long userId, Long goodsId) {
        String quantity = (String) redisTemplate.opsForHash().get(PikaCartConstant.CART_KEY + userId, goodsId);
        if (!Strings.isNullOrEmpty(quantity)) {
            redisTemplate.opsForHash().increment(PikaCartConstant.CART_KEY + userId, goodsId, 1L);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean setQuantity(Long userId, Long goodsId, Integer quantity) {
        redisTemplate.opsForHash().put(PikaCartConstant.CART_KEY + userId, goodsId, quantity);
        return Boolean.TRUE;
    }

    public List<Map.Entry<Long, Integer>> getCartItems(Long userId) {
        Map<Object, Object> originMap = redisTemplate.opsForHash().entries(PikaCartConstant.CART_KEY + userId);
        Map<Long, Integer> map = Maps.newHashMapWithExpectedSize(originMap.size());

        originMap.forEach((key, value) -> map.put( Long.valueOf((String) key), Integer.valueOf((String) value)));

        return Lists.newArrayList(map.entrySet());
    }
}
