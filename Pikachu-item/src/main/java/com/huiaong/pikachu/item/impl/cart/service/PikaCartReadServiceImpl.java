package com.huiaong.pikachu.item.impl.cart.service;

import com.google.common.collect.Lists;
import com.huiaong.pikachu.item.cart.model.PikaCart;
import com.huiaong.pikachu.item.cart.model.PikaCartItem;
import com.huiaong.pikachu.item.cart.service.PikaCartReadService;
import com.huiaong.pikachu.item.goods.model.PikaGoods;
import com.huiaong.pikachu.item.impl.goods.dao.PikaGoodsDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service
public class PikaCartReadServiceImpl implements PikaCartReadService {
    private final String CART_KEY = "PIKA_CART:";
    private final RedisTemplate<String, Object> redisTemplate;
    private final PikaGoodsDao pikaGoodsDao;

    private final Integer DEFAULT_USER_ID = 1;

    @Override
    public PikaCart list() {
        List<PikaCartItem> cartItems = Lists.newArrayList();
        PikaCart cart = new PikaCart();
        PikaCartItem cartItem;

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(CART_KEY + DEFAULT_USER_ID);

        for (Object o : entries.keySet()) {
            Long goodsId = (Long) o;
            cartItem = new PikaCartItem();

            PikaGoods goods = pikaGoodsDao.findById(goodsId);

            BeanUtils.copyProperties(goods, cartItem);

            cartItem.setQuantity((Integer) entries.get(o));

            cartItems.add(cartItem);
        }
        cart.setItems(cartItems);

        return cart;
    }
}
