package com.huiaong.pikachu.admin.goods.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class PikaGoodsVo implements Serializable {

    /**
     * 商品名
     */
    @NotNull
    private String name;

    /**
     * 商品类目
     */
    @NotNull
    private Integer category;

    /**
     * 价格
     */
    @NotNull
    private Long price;

}
