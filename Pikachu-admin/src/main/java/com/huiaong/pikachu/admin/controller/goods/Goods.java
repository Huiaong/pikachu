package com.huiaong.pikachu.admin.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.huiaong.pikachu.admin.annotation.Auth;
import com.huiaong.pikachu.admin.dto.goods.PikaGoodsCO;
import com.huiaong.pikachu.admin.dto.goods.PikaGoodsKindCO;
import com.huiaong.pikachu.admin.dto.goods.PikaGoodsKindUO;
import com.huiaong.pikachu.admin.dto.goods.PikaGoodsUO;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.goods.criteria.PikaGoodsCriteria;
import com.huiaong.pikachu.item.goods.manager.PikaGoodsManager;
import com.huiaong.pikachu.item.goods.model.PikaGoods;
import com.huiaong.pikachu.item.goods.model.PikaGoodsKind;
import com.huiaong.pikachu.item.goods.service.PikaGoodsKindReadService;
import com.huiaong.pikachu.item.goods.service.PikaGoodsReadService;
import com.huiaong.pikachu.resources.dto.PikaGoodsPicture;
import com.huiaong.pikachu.resources.dto.PikaPictureFile;
import com.huiaong.pikachu.resources.service.PikaResourcesWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Api("商品")
@RestController
@RequestMapping("/api/admin/goods")
public class Goods {

    @Reference
    private PikaGoodsReadService pikaGoodsReadService;
    @Reference
    private PikaGoodsKindReadService pikaGoodsKindReadService;
    @Reference
    private PikaResourcesWriteService pikaResourcesWriteService;
    @Reference
    private PikaGoodsManager pikaGoodsManager;

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
    public Response<Boolean> create(@RequestBody @Validated PikaGoodsCO pikaGoodsCO) {
        PikaGoods goods = new PikaGoods();
        PikaGoodsKind goodsKind;
        List<PikaGoodsKind> goodsKinds = Lists.newArrayList();

        BeanUtils.copyProperties(pikaGoodsCO, goods);

        for (PikaGoodsKindCO kind : pikaGoodsCO.getGoodsKinds()) {
            goodsKind = new PikaGoodsKind();
            BeanUtils.copyProperties(kind, goodsKind);
            goodsKinds.add(goodsKind);
        }
        goods.setGoodsKinds(goodsKinds);

        return pikaGoodsManager.create(goods);
    }

    @ApiOperation("查找商品")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response<PikaGoods> findById(@PathVariable(name = "id") Long goodsId) {

        Response<PikaGoods> pikaGoodsResp = pikaGoodsReadService.findById(goodsId);
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

        goods.setGoodsKinds(goodsKindList);

        return Response.ok(goods);
    }

    @ApiOperation("商品图片上传")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Response<PikaGoodsPicture> uploadFile(@RequestParam MultipartFile file) {

        try {
            PikaPictureFile pictureFile = new PikaPictureFile();
            pictureFile.setFilePrefix(file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf(".")));
            pictureFile.setFileSuffix(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            pictureFile.setFileData(file.getBytes());

            return pikaResourcesWriteService.upload(pictureFile);
        } catch (IOException e) {
            log.error("upload picture:{} failed, cause by:{}", file, Throwables.getStackTraceAsString(e));
            return Response.fail("failed.upload.picture");
        }
    }

    @ApiOperation("商品更新")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Response<Boolean> updateGoods(@RequestBody @Validated PikaGoodsUO pikaGoodsUO) {
        if (Objects.isNull(pikaGoodsUO.getId()) || pikaGoodsUO.getGoodsKinds().stream().anyMatch(pikaGoodsKindUO -> Objects.isNull(pikaGoodsKindUO.getId()))) {
            log.error("update PikaGoods:{} failed, cause id not allow null", pikaGoodsUO);
            return Response.fail("failed.to.update.PikaGoods");
        }

        PikaGoods goods = new PikaGoods();
        PikaGoodsKind goodsKind;
        List<PikaGoodsKind> goodsKinds = Lists.newArrayList();

        BeanUtils.copyProperties(pikaGoodsUO, goods);

        for (PikaGoodsKindUO kind : pikaGoodsUO.getGoodsKinds()) {
            goodsKind = new PikaGoodsKind();
            BeanUtils.copyProperties(kind, goodsKind);
            goodsKinds.add(goodsKind);
        }
        goods.setGoodsKinds(goodsKinds);

        return pikaGoodsManager.update(goods);
    }
}
