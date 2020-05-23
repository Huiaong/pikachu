package com.huiaong.pikachu.trade.impl.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.impl.order.dao.PikaPurchaseOrderDao;
import com.huiaong.pikachu.trade.order.criteria.PikaPurchaseOrderCriteria;
import com.huiaong.pikachu.trade.order.model.PikaPurchaseOrder;
import com.huiaong.pikachu.trade.order.service.PikaPurchaseOrderReadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(timeout = 3000)
public class PikaPurchaseOrderReadServiceImpl implements PikaPurchaseOrderReadService {
    private final PikaPurchaseOrderDao pikaPurchaseOrderDao;

    @Override
    public Response<Paging<PikaPurchaseOrder>> paging(PikaPurchaseOrderCriteria criteria) {
        try {
            return Response.ok(pikaPurchaseOrderDao.paging(criteria.toMap()));
        } catch (Exception e) {
            log.error("paging purchase order fail, criteria={}, cause={}", criteria, Throwables.getStackTraceAsString(e));
            return Response.fail("purchase.order.paging.fail");
        }
    }
}
