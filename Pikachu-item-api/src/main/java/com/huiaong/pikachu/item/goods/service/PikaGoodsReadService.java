package com.huiaong.pikachu.item.goods.service;

import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.criteria.PikaGoodsCriteria;
import com.huiaong.pikachu.item.goods.model.PikaGoods;

public interface PikaGoodsReadService {
    Response<PikaGoods> findById(Long id);

    Response<Paging<PikaGoods>> paging(PikaGoodsCriteria criteria);
}
