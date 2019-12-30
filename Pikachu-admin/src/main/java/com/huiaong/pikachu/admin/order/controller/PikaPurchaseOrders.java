package com.huiaong.pikachu.admin.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.trade.order.dto.PikaPurchaseOrderDto;
import com.huiaong.pikachu.trade.order.service.PikaPurchaseOrderReadService;
import com.huiaong.pikachu.trade.order.service.PikaPurchaseOrderWriteService;
import com.huiaong.pikachu.trade.sms.model.NormalMessage;
import com.huiaong.pikachu.trade.sms.service.NormalMessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/purchase-order")
public class PikaPurchaseOrders {

    @Reference
    private NormalMessageService normalMessageService;

    @Reference
    private PikaPurchaseOrderReadService purchaseOrderReadService;

    @Reference
    private PikaPurchaseOrderWriteService pikaPurchaseOrderWriteService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Boolean create(@RequestBody PikaPurchaseOrderDto pikaPurchaseOrderDto){

        return null;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public Boolean cancelOrder(NormalMessage normalMessage) {
        return normalMessageService.create(normalMessage);
    }

}
