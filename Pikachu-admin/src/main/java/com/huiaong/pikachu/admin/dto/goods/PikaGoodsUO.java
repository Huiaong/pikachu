package com.huiaong.pikachu.admin.dto.goods;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class PikaGoodsUO extends PikaGoodsCO {

    @NotNull(message = "商品id不能为空")
    private Long id;
}
