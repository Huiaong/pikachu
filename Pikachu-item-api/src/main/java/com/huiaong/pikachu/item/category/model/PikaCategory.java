package com.huiaong.pikachu.item.category.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PikaCategory extends PikaBaseBean {

    /**
     * 名称
     */
    private String name;

    /**
     * 是否有下级类目
     */
    private Boolean hasChildren;

    /**
     * 类目状态
     * @see com.huiaong.pikachu.item.category.enums.PikaCategoryStatus
     */
    private Integer status;

    /**
     * 父类目ID
     */
    private Long parentId;

}
