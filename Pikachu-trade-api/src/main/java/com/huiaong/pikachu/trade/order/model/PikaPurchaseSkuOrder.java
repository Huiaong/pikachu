package com.huiaong.pikachu.trade.order.model;

import com.huiaong.pikachu.common.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaPurchaseSkuOrder extends PikaBaseBean {

    private Long purchaseOrderId;

    private Long itemId;

    private Integer status;

    private Integer quantity;

    private Long buyerId;

    private String buyerNote;
}
