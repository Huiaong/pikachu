package com.huiaong.pikachu.user.auth.criteria;

import com.huiaong.pikachu.common.pager.PagingCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaRoleCriteria extends PagingCriteria {

    private Long id;

    private String value;
}
