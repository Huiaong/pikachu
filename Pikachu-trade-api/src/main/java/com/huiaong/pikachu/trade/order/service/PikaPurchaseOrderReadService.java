package com.huiaong.pikachu.trade.order.service;

import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.order.criteria.PikaPurchaseOrderCriteria;
import com.huiaong.pikachu.trade.order.model.PikaPurchaseOrder;

public interface PikaPurchaseOrderReadService {
    Response<Paging<PikaPurchaseOrder>> paging(PikaPurchaseOrderCriteria criteria);
}
