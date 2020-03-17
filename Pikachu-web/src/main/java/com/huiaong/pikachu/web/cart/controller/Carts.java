package com.huiaong.pikachu.web.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.cart.model.PikaCart;
import com.huiaong.pikachu.item.cart.service.PikaCartReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/web/cart")
public class Carts {

    @Reference
    private PikaCartReadService pikaCartReadService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PikaCart list() {
        Response<PikaCart> cartResp = pikaCartReadService.list();
        if (!cartResp.isSuccess()){
            log.error("list cart failed, cause by:{}", cartResp.getError());
        }
        return cartResp.getResult();
    }

    @RequestMapping(value = "/add-item/{id}", method = RequestMethod.GET)
    public Boolean addToCart(@PathVariable(name = "id") Long goodsId) {
        Response<Boolean> booleanResp = pikaCartReadService.addToCart(goodsId);
        if (!booleanResp.isSuccess()){
            log.error("add:{} to cart failed, cause by:{}", goodsId, booleanResp.getError());
        }
        return booleanResp.getResult();
    }

    @RequestMapping(value = "/increment/{id}", method = RequestMethod.GET)
    public Boolean increment(@PathVariable(name = "id") Long goodsId,
                             @RequestBody(required = false) Integer quantity) {
        if (Objects.isNull(quantity)) {
            //数量为空 做 increment

            Response<Boolean> booleanResp = pikaCartReadService.incr(goodsId);
            if (!booleanResp.isSuccess()){
                log.error("incr:{} quantity failed, cause by:{}", goodsId, booleanResp.getError());
            }
            return booleanResp.getResult();
        }
        //数量不为空 做 设置数量
        Response<Boolean> booleanResp = pikaCartReadService.setQuantity(goodsId, quantity);
        if (!booleanResp.isSuccess()){
            log.error("set:{} quantity:{} failed, cause by:{}", goodsId, quantity, booleanResp.getError());
        }
        return booleanResp.getResult();
    }
}
