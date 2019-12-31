package com.huiaong.pikachu.trade.order.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.order.dto.PikaPurchaseOrderDto;

public interface PikaPurchaseOrderWriteService {
    Response<Boolean> create(PikaPurchaseOrderDto pikaPurchaseOrderDto);
}
