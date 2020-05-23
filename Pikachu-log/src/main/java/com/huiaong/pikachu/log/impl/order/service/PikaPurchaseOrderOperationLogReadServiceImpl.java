package com.huiaong.pikachu.log.impl.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.log.impl.order.dao.PikaPurchaseOrderOperationLogDao;
import com.huiaong.pikachu.log.order.criteria.PikaPurchaseOrderOperationLogCriteria;
import com.huiaong.pikachu.log.order.model.PikaPurchaseOrderOperationLog;
import com.huiaong.pikachu.log.order.service.PikaPurchaseOrderOperationLogReadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(timeout = 3000)
public class PikaPurchaseOrderOperationLogReadServiceImpl implements PikaPurchaseOrderOperationLogReadService {
    private final PikaPurchaseOrderOperationLogDao purchaseOrderOperationLogDao;

    public Response<Paging<PikaPurchaseOrderOperationLog>> paging(PikaPurchaseOrderOperationLogCriteria criteria) {
        try {
            return Response.ok(purchaseOrderOperationLogDao.paging(criteria.toMap()));
        } catch (Exception e) {
            log.error("paging purchase order log fail, criteria={}, cause={}", criteria, Throwables.getStackTraceAsString(e));
            return Response.fail("purchase.order.log.paging.fail");
        }
    }
}
