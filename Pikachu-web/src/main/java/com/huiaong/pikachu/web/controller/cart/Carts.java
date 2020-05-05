package com.huiaong.pikachu.web.controller.cart;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.cart.model.PikaCart;
import com.huiaong.pikachu.item.cart.service.PikaCartReadService;
import com.huiaong.pikachu.item.cart.service.PikaCartWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Api("购物车")
@RestController
@RequestMapping("/api/web/cart")
public class Carts {

    private final Long DEFAULT_USER_ID = 1L;
    @Reference
    private PikaCartReadService pikaCartReadService;
    @Reference
    private PikaCartWriteService pikaCartWriteService;

    @ApiOperation("购物车商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PikaCart list() {
        Response<PikaCart> cartResp = pikaCartReadService.list(DEFAULT_USER_ID);
        if (!cartResp.isSuccess()) {
            log.error("list cart failed, cause by:{}", cartResp.getError());
        }
        return cartResp.getResult();
    }

    @ApiOperation("购物车添加商品")
    @RequestMapping(value = "/add-item/{id}", method = RequestMethod.GET)
    public Boolean addToCart(@PathVariable(name = "id") Long goodsId) {
        Response<Boolean> booleanResp = pikaCartWriteService.addToCart(DEFAULT_USER_ID, goodsId);
        if (!booleanResp.isSuccess()) {
            log.error("add:{} to cart failed, cause by:{}", goodsId, booleanResp.getError());
        }
        return booleanResp.getResult();
    }

    @ApiOperation("购物车商品数量增加")
    @RequestMapping(value = "/increment/{id}", method = RequestMethod.GET)
    public Boolean increment(@PathVariable(name = "id") Long goodsId,
                             @RequestBody(required = false) Integer quantity) {
        if (Objects.isNull(quantity)) {
            //数量为空 做 increment

            Response<Boolean> booleanResp = pikaCartWriteService.incr(DEFAULT_USER_ID, goodsId);
            if (!booleanResp.isSuccess()) {
                log.error("incr:{} quantity failed, cause by:{}", goodsId, booleanResp.getError());
            }
            return booleanResp.getResult();
        }
        //数量不为空 做 设置数量
        Response<Boolean> booleanResp = pikaCartWriteService.setQuantity(DEFAULT_USER_ID, goodsId, quantity);
        if (!booleanResp.isSuccess()) {
            log.error("set:{} quantity:{} failed, cause by:{}", goodsId, quantity, booleanResp.getError());
        }
        return booleanResp.getResult();
    }
}
