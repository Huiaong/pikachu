package com.huiaong.pikachu.trade.impl.order.service;

import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.exception.DataPersistenceException;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.impl.order.manage.PikaPurchaseOrderManager;
import com.huiaong.pikachu.trade.order.dto.PikaPurchaseOrderDto;
import com.huiaong.pikachu.trade.order.service.PikaPurchaseOrderWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service
public class PikaPurchaseOrderWriteServiceImpl implements PikaPurchaseOrderWriteService {

    private final PikaPurchaseOrderManager pikaPurchaseOrderManager;

    @Override
    public Response<Boolean> create(PikaPurchaseOrderDto pikaPurchaseOrderDto) {
        try {
            return Response.ok(pikaPurchaseOrderManager.create(pikaPurchaseOrderDto));
        } catch (DataPersistenceException e) {
            log.error("create purchase order dto:{} failed, cause by:{}", pikaPurchaseOrderDto, Throwables.getStackTraceAsString(e));
            return Response.fail("purchase.order.dto.create.failed");
        }
    }
}
