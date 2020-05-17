package com.huiaong.pikachu.admin.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.log.order.criteria.PikaPurchaseOrderOperationLogCriteria;
import com.huiaong.pikachu.log.order.model.PikaPurchaseOrderOperationLog;
import com.huiaong.pikachu.log.order.service.PikaPurchaseOrderOperationLogReadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api("采购单日志")
@RestController
@RequestMapping("/api/admin/purchase-operation-log")
public class PurchaseOrderOperationLogs {

    @Reference
    private PikaPurchaseOrderOperationLogReadService purchaseOrderOperationLogReadService;

    @ApiOperation("采购单操作日志分页")
    @RequestMapping(value = "/paging", method = RequestMethod.GET)
    public Paging<PikaPurchaseOrderOperationLog> paging(PikaPurchaseOrderOperationLogCriteria criteria) {
        Response<Paging<PikaPurchaseOrderOperationLog>> pagingResponse = purchaseOrderOperationLogReadService.paging(criteria);
        if (!pagingResponse.isSuccess()) {
            log.error("paging by purchase order operation log criteria:{} failed, cause by:{}", criteria, pagingResponse.getError());
        }
        return pagingResponse.getResult();
    }
}
