package com.huiaong.pikachu.item.cart.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.cart.model.PikaCart;

public interface PikaCartReadService {
    Response<PikaCart> list();

    Response<Boolean> addToCart(Long goodsId);

    Response<Boolean> incr(Long goodsId);

    Response<Boolean> setQuantity(Long goodsId, Integer quantity);
}
