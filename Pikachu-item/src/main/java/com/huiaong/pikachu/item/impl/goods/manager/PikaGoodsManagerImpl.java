package com.huiaong.pikachu.item.impl.goods.manager;


import com.alibaba.dubbo.config.annotation.Service;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.manager.PikaGoodsManager;
import com.huiaong.pikachu.item.goods.model.PikaGoods;
import com.huiaong.pikachu.item.goods.model.PikaGoodsKind;
import com.huiaong.pikachu.item.impl.goods.dao.PikaGoodsDao;
import com.huiaong.pikachu.item.impl.goods.dao.PikaGoodsKindDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service(cluster = "failfast", timeout = 3000)
public class PikaGoodsManagerImpl implements PikaGoodsManager {
    private final PikaGoodsDao pikaGoodsDao;
    private final PikaGoodsKindDao pikaGoodsKindDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> create(PikaGoods goods) {
        Boolean aBoolean = pikaGoodsDao.create(goods);
        List<PikaGoodsKind> goodsKinds = goods.getGoodsKinds();
        goodsKinds.forEach(pikaGoodsKind -> {
            pikaGoodsKind.setGoodsId(goods.getId());
            pikaGoodsKind.setName(goods.getName());
        });
        Integer createCount = pikaGoodsKindDao.creates(goodsKinds);
        if (aBoolean && createCount == goodsKinds.size()){
            return Response.ok();
        }
        log.error("create PikaGoods:{} with transactional fail", goods);
        return Response.fail("failed.to.create.PikaGoods");
    }

    @Override
    public Response<Boolean> update(PikaGoods goods) {
        Boolean aBoolean = pikaGoodsDao.update(goods);
        List<PikaGoodsKind> goodsKinds = goods.getGoodsKinds();
        goodsKinds.forEach(pikaGoodsKindDao::update);
        if (aBoolean){
            return Response.ok();
        }
        log.error("update PikaGoods:{} with transactional fail", goods);
        return Response.fail("failed.to.update.PikaGoods");
    }
}
