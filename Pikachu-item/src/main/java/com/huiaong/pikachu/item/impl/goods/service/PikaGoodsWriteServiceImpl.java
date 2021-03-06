package com.huiaong.pikachu.item.impl.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.exception.DataPersistenceException;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.enums.PikaGoodsPriceType;
import com.huiaong.pikachu.item.goods.enums.PikaGoodsStatus;
import com.huiaong.pikachu.item.goods.model.PikaGoods;
import com.huiaong.pikachu.item.goods.service.PikaGoodsWriteService;
import com.huiaong.pikachu.item.impl.goods.dao.PikaGoodsDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(cluster = "failfast", timeout = 3000)
public class PikaGoodsWriteServiceImpl implements PikaGoodsWriteService {
    private final PikaGoodsDao pikaGoodsDao;

    @Override
    public Response<Boolean> create(PikaGoods goods) {

        goods.setCreateId(1L);
        goods.setDiscount(0L);
        goods.setPriceType(PikaGoodsPriceType.NORMAL.value());
        goods.setStatus(PikaGoodsStatus.CREATED.value());

        try {
            return Response.ok(pikaGoodsDao.create(goods));
        } catch (DataPersistenceException e) {
            log.error("create goods:{} failed, cause by:{}", goods, Throwables.getStackTraceAsString(e));
            return Response.fail("goods.create.failed");
        }
    }

}
