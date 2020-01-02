package com.huiaong.pikachu.admin.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.order.criteria.PikaPurchaseOrderCriteria;
import com.huiaong.pikachu.trade.order.dto.PikaPurchaseOrderDto;
import com.huiaong.pikachu.trade.order.model.PikaPurchaseOrder;
import com.huiaong.pikachu.trade.order.service.PikaPurchaseOrderReadService;
import com.huiaong.pikachu.trade.order.service.PikaPurchaseOrderWriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin/purchase-order")
public class PurchaseOrders {

    @Reference
    private PikaPurchaseOrderReadService pikaPurchaseOrderReadService;
    @Reference
    private PikaPurchaseOrderWriteService pikaPurchaseOrderWriteService;

    @RequestMapping(value = "/paging", method = RequestMethod.GET)
    public Paging<PikaPurchaseOrder> paging(PikaPurchaseOrderCriteria criteria) {
        Response<Paging<PikaPurchaseOrder>> pagingResponse = pikaPurchaseOrderReadService.paging(criteria);
        if (!pagingResponse.isSuccess()) {
            log.error("paging by purchase order criteria:{} failed, cause by:{}", criteria, pagingResponse.getError());
        }
        return pagingResponse.getResult();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Boolean create(@RequestBody PikaPurchaseOrderDto pikaPurchaseOrderDto) {

        Response<Boolean> booleanResponse = pikaPurchaseOrderWriteService.create(pikaPurchaseOrderDto);

        if (!booleanResponse.isSuccess()) {
            log.error("create purchase order dto:{} failed, cause by:{}", pikaPurchaseOrderDto, booleanResponse.getError());
        }
        return booleanResponse.getResult();
    }

}
