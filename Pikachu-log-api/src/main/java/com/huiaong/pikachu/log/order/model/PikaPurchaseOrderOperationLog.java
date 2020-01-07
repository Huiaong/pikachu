package com.huiaong.pikachu.log.order.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import com.huiaong.pikachu.log.order.enums.PikaPurchaseOrderOperationLogType;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class PikaPurchaseOrderOperationLog extends PikaBaseBean {

    private Long purchaseOrderId;

    /**
     * @see PikaPurchaseOrderOperationLogType
     */
    private Long operationType;
}
