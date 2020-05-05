package com.huiaong.pikachu.admin.QO.goods;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class PikaGoodsQO implements Serializable {

    /**
     * 商品名
     */
    @NotBlank
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
