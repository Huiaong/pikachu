package com.huiaong.pikachu.trade.order.module;

import com.huiaong.pikachu.trade.base.module.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaPurchaseSkuOrder extends PikaBaseBean {

    private Long purchaseOrderId;

    private Long itemId;

    private Integer status;

    private Integer quantity;

    private String buyerNote;
}
