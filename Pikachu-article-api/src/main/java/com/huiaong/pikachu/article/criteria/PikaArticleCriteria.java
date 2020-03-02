package com.huiaong.pikachu.article.criteria;

import com.huiaong.pikachu.common.pager.PagingCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
public class PikaArticleCriteria extends PagingCriteria implements Serializable {
    private Long id;
}
