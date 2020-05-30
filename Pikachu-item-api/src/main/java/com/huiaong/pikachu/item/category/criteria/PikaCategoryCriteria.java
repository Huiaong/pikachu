package com.huiaong.pikachu.item.category.criteria;

import com.huiaong.pikachu.common.pager.PagingCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaCategoryCriteria extends PagingCriteria implements Serializable {
    private Long id;

    private String name;

}
