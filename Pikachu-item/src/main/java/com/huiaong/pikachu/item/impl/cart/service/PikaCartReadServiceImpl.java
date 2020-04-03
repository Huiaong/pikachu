package com.huiaong.pikachu.item.impl.cart.service;

import com.google.common.collect.Lists;
import com.huiaong.pikachu.common.response.Response;
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
    private final Long DEFAULT_USER_ID = 1L;
    private final Integer DEFAULT_QUANTITY = 1;

    private final RedisTemplate<String, Object> redisTemplate;
    private final PikaGoodsDao pikaGoodsDao;


    @Override
    public Response<PikaCart> list() {
        List<PikaCartItem> cartItems = Lists.newArrayList();
        PikaCart cart = new PikaCart();
        PikaCartItem cartItem;

        cart.setUserId(DEFAULT_USER_ID);

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(CART_KEY + DEFAULT_USER_ID);

        for (Object o : entries.keySet()) {
            Long goodsId = Long.valueOf((String) o);
            cartItem = new PikaCartItem();

            PikaGoods goods = pikaGoodsDao.findById(goodsId);

            BeanUtils.copyProperties(goods, cartItem);

            cartItem.setQuantity((Integer) entries.get(o));

            cartItems.add(cartItem);
        }
        cart.setItems(cartItems);

        return Response.ok(cart);
    }

}
