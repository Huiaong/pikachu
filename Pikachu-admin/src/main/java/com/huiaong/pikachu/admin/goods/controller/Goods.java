package com.huiaong.pikachu.admin.goods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.criteria.PikaGoodsCriteria;
import com.huiaong.pikachu.admin.goods.vo.PikaGoodsVo;
import com.huiaong.pikachu.item.goods.model.PikaGoods;
import com.huiaong.pikachu.item.goods.service.PikaGoodsReadService;
import com.huiaong.pikachu.item.goods.service.PikaGoodsWriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/admin/goods")
public class Goods {

    @Reference
    private PikaGoodsReadService pikaGoodsReadService;
    @Reference
    private PikaGoodsWriteService pikaGoodsWriteService;

    @RequestMapping(value = "/paging", method = RequestMethod.GET)
    public Paging<PikaGoods> paging(PikaGoodsCriteria criteria) {
        Response<Paging<PikaGoods>> pagingResp = pikaGoodsReadService.paging(criteria);
        if (!pagingResp.isSuccess()) {
            log.error("paging by goods criteria:{} failed, cause by:{}", criteria, pagingResp.getError());
        }
        return pagingResp.getResult();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Boolean create(@RequestBody @Validated PikaGoodsVo pikaGoodsVo) {
        PikaGoods goods = new PikaGoods();

        BeanUtils.copyProperties(pikaGoodsVo, goods);

        Response<Boolean> booleanResp = pikaGoodsWriteService.create(goods);

        if (!booleanResp.isSuccess()) {
            log.error("create goods vo:{} failed, cause by:{}", pikaGoodsVo, booleanResp.getError());
        }
        return booleanResp.getResult();
    }
}
