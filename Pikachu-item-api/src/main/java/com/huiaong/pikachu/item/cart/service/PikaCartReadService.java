package com.huiaong.pikachu.item.cart.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.cart.model.PikaCart;

public interface PikaCartReadService {
    Response<PikaCart> list();
}
