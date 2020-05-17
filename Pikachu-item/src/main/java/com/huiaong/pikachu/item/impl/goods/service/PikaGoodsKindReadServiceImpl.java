package com.huiaong.pikachu.item.impl.goods.service;

import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.model.PikaGoodsKind;
import com.huiaong.pikachu.item.goods.service.PikaGoodsKindReadService;
import com.huiaong.pikachu.item.impl.goods.dao.PikaGoodsKindDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(timeout = 3000)
public class PikaGoodsKindReadServiceImpl implements PikaGoodsKindReadService {
    private final PikaGoodsKindDao pikaGoodsKindDao;

    @Override
    public Response<List<PikaGoodsKind>> findByGoodsId(Long goodsId) {
        try {
            return Response.ok(pikaGoodsKindDao.findByGoodsId(goodsId));
        } catch (Exception e) {
            log.error("find goods kind by goods id:{} fail, cause={}", goodsId, Throwables.getStackTraceAsString(e));
            return Response.fail("goods.kind.find.fail");
        }
    }
}
