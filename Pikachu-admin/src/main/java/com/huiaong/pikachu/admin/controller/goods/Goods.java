package com.huiaong.pikachu.admin.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.huiaong.pikachu.admin.QO.goods.PikaGoodsQO;
import com.huiaong.pikachu.admin.VO.goods.PikaGoodsKindVO;
import com.huiaong.pikachu.admin.VO.goods.PikaGoodsVO;
import com.huiaong.pikachu.admin.annotation.Auth;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.criteria.PikaGoodsCriteria;
import com.huiaong.pikachu.item.goods.model.PikaGoods;
import com.huiaong.pikachu.item.goods.model.PikaGoodsKind;
import com.huiaong.pikachu.item.goods.service.PikaGoodsKindReadService;
import com.huiaong.pikachu.item.goods.service.PikaGoodsKindWriteService;
import com.huiaong.pikachu.item.goods.service.PikaGoodsReadService;
import com.huiaong.pikachu.item.goods.service.PikaGoodsWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api("商品")
@RestController
@RequestMapping("/api/admin/goods")
public class Goods {

    @Reference
    private PikaGoodsReadService pikaGoodsReadService;
    @Reference
    private PikaGoodsWriteService pikaGoodsWriteService;
    @Reference
    private PikaGoodsKindReadService pikaGoodsKindReadService;
    @Reference
    private PikaGoodsKindWriteService pikaGoodsKindWriteService;

    @Auth("a")
    @ApiOperation("商品分页")
    @RequestMapping(value = "/paging", method = RequestMethod.GET)
    public Response<Paging<PikaGoods>> paging(PikaGoodsCriteria criteria) {
        Response<Paging<PikaGoods>> pagingResp = pikaGoodsReadService.paging(criteria);
        if (!pagingResp.isSuccess()) {
            log.error("paging by goods criteria:{} failed, cause by:{}", criteria, pagingResp.getError());
        }
        return pagingResp;
    }

    @ApiOperation("创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Boolean create(@RequestBody @Validated PikaGoodsQO pikaGoodsQO) {
        PikaGoods goods = new PikaGoods();

        BeanUtils.copyProperties(pikaGoodsQO, goods);

        Response<Boolean> booleanResp = pikaGoodsWriteService.create(goods);

        if (!booleanResp.isSuccess()) {
            log.error("create goods vo:{} failed, cause by:{}", pikaGoodsQO, booleanResp.getError());
        }
        return booleanResp.getResult();
    }

    @ApiOperation("查找商品")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response<PikaGoodsVO> findById(@PathVariable(name = "id") Long goodsId) {

        Response<PikaGoods> pikaGoodsResp = pikaGoodsWriteService.findById(goodsId);
        if (!pikaGoodsResp.isSuccess()) {
            log.error("find goods by id:{} failed, cause by:{}", goodsId, pikaGoodsResp.getError());
            return Response.fail(pikaGoodsResp.getError());
        }
        PikaGoods goods = pikaGoodsResp.getResult();

        Response<List<PikaGoodsKind>> pikaGoodsKindListResp = pikaGoodsKindReadService.findByGoodsId(goodsId);
        if (!pikaGoodsKindListResp.isSuccess()) {
            log.error("find goods kind by goods id:{} failed, cause by:{}", goodsId, pikaGoodsResp.getError());
            return Response.fail(pikaGoodsKindListResp.getError());
        }
        List<PikaGoodsKind> goodsKindList = pikaGoodsKindListResp.getResult();

        PikaGoodsVO pikaGoodsVO = new PikaGoodsVO();
        BeanUtils.copyProperties(goods, pikaGoodsVO);

        List<PikaGoodsKindVO> goodsKind = Lists.newArrayList();
        PikaGoodsKindVO pikaGoodsKindVO;
        for (PikaGoodsKind pikaGoodsKind : goodsKindList) {
            pikaGoodsKindVO = new PikaGoodsKindVO();
            BeanUtils.copyProperties(pikaGoodsKind, pikaGoodsKindVO);
            goodsKind.add(pikaGoodsKindVO);
        }

        pikaGoodsVO.setGoodsKind(goodsKind);

        return Response.ok(pikaGoodsVO);
    }
}
