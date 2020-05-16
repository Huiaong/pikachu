package com.huiaong.pikachu.item.goods.criteria;

import com.huiaong.pikachu.common.pager.PagingCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaGoodsCriteria extends PagingCriteria implements Serializable {
    private Long id;

    private Integer category;
}
