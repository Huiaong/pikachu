package com.huiaong.pikachu.item.impl.cart.service;

import com.google.common.collect.Lists;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.cart.model.PikaCart;
import com.huiaong.pikachu.item.cart.model.PikaCartItem;
import com.huiaong.pikachu.item.cart.service.PikaCartReadService;
import com.huiaong.pikachu.item.goods.model.PikaGoods;
import com.huiaong.pikachu.item.impl.cart.dao.PikaCartDao;
import com.huiaong.pikachu.item.impl.goods.dao.PikaGoodsDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(timeout = 3000)
public class PikaCartReadServiceImpl implements PikaCartReadService {
    private final PikaGoodsDao pikaGoodsDao;
    private final PikaCartDao pikaCartDao;

    @Override
    public Response<PikaCart> list(Long userId) {
        List<PikaCartItem> cartItems = Lists.newArrayList();
        PikaCart cart = new PikaCart();
        PikaCartItem cartItem;

        List<Map.Entry<Long, Integer>> items = pikaCartDao.getCartItems(userId);

        for (Map.Entry<Long, Integer> item : items) {
            cartItem = new PikaCartItem();
            PikaGoods goods = pikaGoodsDao.findById(item.getKey());
            BeanUtils.copyProperties(goods, cartItem);
            cartItem.setQuantity(item.getValue());
            cartItems.add(cartItem);
        }
        cart.setItems(cartItems);

        return Response.ok(cart);
    }

}
