package com.huiaong.pikachu.log.order.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.log.order.model.PikaPurchaseOrderOperationLog;

public interface PikaPurchaseOrderOperationLogWriteService {
    Response<Boolean> create(PikaPurchaseOrderOperationLog purchaseOrderOperationLog);
}
