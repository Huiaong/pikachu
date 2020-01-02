package com.huiaong.pikachu.log.order.criteria;

import com.huiaong.pikachu.common.pager.PagingCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaPurchaseOrderOperationLogCriteria extends PagingCriteria implements Serializable {
    private Long id;
}
