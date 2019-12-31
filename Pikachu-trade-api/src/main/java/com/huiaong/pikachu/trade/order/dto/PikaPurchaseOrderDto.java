package com.huiaong.pikachu.trade.order.dto;

import com.huiaong.pikachu.trade.order.model.PikaPurchaseOrder;
import com.huiaong.pikachu.trade.order.model.PikaPurchaseSkuOrder;
import lombok.Data;

import java.io.Serializable;

@Data
public class PikaPurchaseOrderDto implements Serializable {

    private PikaPurchaseOrder purchaseOrder;

    private PikaPurchaseSkuOrder purchaseSkuOrder;

}
