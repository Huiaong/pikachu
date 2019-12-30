package com.huiaong.pikachu.trade.order.module;

import com.huiaong.pikachu.trade.base.module.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaPurchaseOrder extends PikaBaseBean {

    private String purchaseOrderCode;

    private String contractCode;

    private Long buyerId;

    private String buyerNote;

    /**
     * @see com.huiaong.pikachu.trade.order.enums.PikaPurchaseOrderStatus
     */
    private Integer status;

    /**
     * @see com.huiaong.pikachu.trade.order.enums.PikaPurchaseOrderTypes
     */
    private Integer type;

    private Long categoryId;

}
