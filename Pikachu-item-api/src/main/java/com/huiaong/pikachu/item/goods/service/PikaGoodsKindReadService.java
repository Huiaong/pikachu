package com.huiaong.pikachu.item.goods.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.model.PikaGoodsKind;

import java.util.List;

public interface PikaGoodsKindReadService {

    Response<List<PikaGoodsKind>> findByGoodsId(Long goodsId);
}
