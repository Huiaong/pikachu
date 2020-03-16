package com.huiaong.pikachu.web.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.item.cart.model.PikaCart;
import com.huiaong.pikachu.item.cart.service.PikaCartReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/web/cart")
public class Carts {

    @Reference
    private PikaCartReadService pikaCartReadService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PikaCart list() {
        return pikaCartReadService.list();
    }
}
