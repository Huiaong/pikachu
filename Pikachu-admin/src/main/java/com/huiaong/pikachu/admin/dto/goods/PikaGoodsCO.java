package com.huiaong.pikachu.admin.dto.goods;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
public class PikaGoodsCO implements Serializable {

    /**
     * 商品名
     */
    @NotBlank(message = "商品名不能为空")
    private String name;

    /**
     * 商品类目
     */
    @NotNull(message = "商品类目不能为空")
    private Integer category;

    /**
     * 价格
     */
    @NotNull(message = "商品价格不能为空")
    private Long price;

    /**
     * 商品介绍
     */
    @NotBlank(message = "商品详情不能为空")
    private String desc;

    /**
     * 商品种类
     */
    @NotNull(message = "商品种类不能为空")
    private List<PikaGoodsKindCO> goodsKinds;

}
