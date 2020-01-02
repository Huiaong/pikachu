package com.huiaong.pikachu.log.order.service;

import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.log.order.criteria.PikaPurchaseOrderOperationLogCriteria;
import com.huiaong.pikachu.log.order.model.PikaPurchaseOrderOperationLog;

public interface PikaPurchaseOrderOperationLogReadService {

    Response<Paging<PikaPurchaseOrderOperationLog>> paging(PikaPurchaseOrderOperationLogCriteria criteria);
}
