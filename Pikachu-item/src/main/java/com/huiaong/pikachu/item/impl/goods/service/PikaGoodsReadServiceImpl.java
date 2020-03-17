package com.huiaong.pikachu.item.impl.goods.service;

import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.criteria.PikaGoodsCriteria;
import com.huiaong.pikachu.item.goods.model.PikaGoods;
import com.huiaong.pikachu.item.goods.service.PikaGoodsReadService;
import com.huiaong.pikachu.item.impl.goods.dao.PikaGoodsDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service
public class PikaGoodsReadServiceImpl implements PikaGoodsReadService {
    private final PikaGoodsDao pikaGoodsDao;

    @Override
    public Response<PikaGoods> findById(Long id) {
        try {
            return Response.ok(pikaGoodsDao.findById(id));
        } catch (Exception e) {
            log.error("find goods by id:{} fail, cause={}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("goods.find.fail");
        }
    }

    @Override
    public Response<Paging<PikaGoods>> paging(PikaGoodsCriteria criteria) {
        try {
            return Response.ok(pikaGoodsDao.paging(criteria.toMap()));
        } catch (Exception e) {
            log.error("find goods by criteria:{} fail, cause={}", criteria, Throwables.getStackTraceAsString(e));
            return Response.fail("goods.find.fail");
        }
    }
}
