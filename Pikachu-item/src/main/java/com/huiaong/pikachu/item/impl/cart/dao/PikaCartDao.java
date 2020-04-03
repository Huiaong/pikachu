package com.huiaong.pikachu.item.impl.cart.dao;

import com.google.common.collect.Lists;
import com.huiaong.pikachu.item.cart.constant.PikaCartConstant;
import lombok.AllArgsConstructor;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class PikaCartDao {
    private final RedissonClient redissonClient;

    public Boolean addToCart(Long userId, Long goodsId) {
        RMap<Long, Integer> rMap = redissonClient.getMap(PikaCartConstant.CART_KEY + userId);
        Integer quantity;
        if (Objects.isNull(quantity = rMap.get(goodsId))) {
            rMap.fastPut(goodsId, 1);
        } else {
            rMap.fastPut(goodsId, 1 + quantity);
        }
        return Boolean.TRUE;
    }

    public Boolean incr(Long userId, Long goodsId) {
        RMap<Long, Integer> rMap = redissonClient.getMap(PikaCartConstant.CART_KEY + userId);
        Integer quantity;
        if (!Objects.isNull(quantity = rMap.get(goodsId))) {
            rMap.fastPut(goodsId, 1 + quantity);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean setQuantity(Long userId, Long goodsId, Integer quantity) {
        RMap<Long, Integer> rMap = redissonClient.getMap(PikaCartConstant.CART_KEY + userId);
        rMap.fastPut(goodsId, quantity);
        return Boolean.TRUE;
    }

    public List<Map.Entry<Long, Integer>> getCartItems(Long userId) {
        RMap<Long, Integer> rMap = redissonClient.getMap(PikaCartConstant.CART_KEY + userId);
        return Lists.newArrayList(rMap.entrySet());
    }
}
