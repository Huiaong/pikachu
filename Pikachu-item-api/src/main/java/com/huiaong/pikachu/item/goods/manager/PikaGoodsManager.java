package com.huiaong.pikachu.item.goods.manager;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.model.PikaGoods;

public interface PikaGoodsManager {
    Response<Boolean> create(PikaGoods goods);

    Response<Boolean> update(PikaGoods goods);
}
