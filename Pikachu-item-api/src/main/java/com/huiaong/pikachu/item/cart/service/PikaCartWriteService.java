package com.huiaong.pikachu.item.cart.service;

import com.huiaong.pikachu.common.response.Response;

public interface PikaCartWriteService {

    Response<Boolean> addToCart(Long userId, Long goodsId);

    Response<Boolean> incr(Long userId, Long goodsId);

    Response<Boolean> setQuantity(Long userId, Long goodsId, Integer quantity);
}
