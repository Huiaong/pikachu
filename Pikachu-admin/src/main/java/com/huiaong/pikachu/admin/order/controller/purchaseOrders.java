package com.huiaong.pikachu.admin.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.trade.sms.model.NormalMessage;
import com.huiaong.pikachu.trade.sms.service.NormalMessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/purchase-order")
public class purchaseOrders {

    @Reference
    private NormalMessageService normalMessageService;


    @RequestMapping(value = "/order-create", method = RequestMethod.POST)
    public Boolean cancelOrder(NormalMessage normalMessage) {
        return normalMessageService.create(normalMessage);
    }

}
