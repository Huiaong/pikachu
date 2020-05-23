package com.huiaong.pikachu.log.impl.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.log.impl.order.dao.PikaPurchaseOrderOperationLogDao;
import com.huiaong.pikachu.log.order.model.PikaPurchaseOrderOperationLog;
import com.huiaong.pikachu.log.order.service.PikaPurchaseOrderOperationLogWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(cluster = "failfast", timeout = 3000)
public class PikaPurchaseOrderOperationLogWriteServiceImpl implements PikaPurchaseOrderOperationLogWriteService {

    private final PikaPurchaseOrderOperationLogDao purchaseOrderOperationLogDao;

    @Override
    public Response<Boolean> create(PikaPurchaseOrderOperationLog purchaseOrderOperationLog) {
        try {
            return Response.ok(purchaseOrderOperationLogDao.create(purchaseOrderOperationLog));
        } catch (Exception e) {
            log.error("create purchase order operation log:{} failed, cause by:{}", purchaseOrderOperationLog, Throwables.getStackTraceAsString(e));
            return Response.fail("purchase.order.operation.log.create.failed");
        }
    }
}
