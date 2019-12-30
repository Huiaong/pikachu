package com.huiaong.pikachu.trade.order.dto;

import com.huiaong.pikachu.trade.order.module.PikaPurchaseOrder;
import com.huiaong.pikachu.trade.order.module.PikaPurchaseSkuOrder;
import lombok.Data;

import java.io.Serializable;

@Data
public class PikaPurchaseOrderDto implements Serializable {

    private PikaPurchaseOrder purchaseOrder;

    private PikaPurchaseSkuOrder purchaseSkuOrder;

}
