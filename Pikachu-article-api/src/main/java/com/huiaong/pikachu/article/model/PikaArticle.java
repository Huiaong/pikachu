package com.huiaong.pikachu.article.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaArticle extends PikaBaseBean {

    /**
     * @see com.huiaong.pikachu.article.enums.PikaArticleType
     */
    private Integer type;

    /**
     * @see com.huiaong.pikachu.article.enums.PikaArticleStatus
     */
    private Integer status;

    private String title;

    private String description;

    private String content;
}
