package com.huiaong.pikachu.item.goods.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaGoods extends PikaBaseBean {

    private String name;

    /**
     * 商品类目
     */
    private Integer category;

    /**
     * 状态
     * @see com.huiaong.pikachu.item.goods.enums.PikaGoodsStatus
     */
    private Integer status;

    /**
     * code
     */
    private String code;

    /**
     * 价格
     */
    private Long price;

    /**
     * 折扣
     */
    private Long discount;

    /**
     * 降价情况
     * @see com.huiaong.pikachu.item.goods.enums.PikaGoodsPriceType
     */
    private Integer priceType;

}
