package com.huiaong.pikachu.item.goods.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.model.PikaGoods;

public interface PikaGoodsWriteService {
    Response<Boolean> create(PikaGoods pikaGoods);
}
