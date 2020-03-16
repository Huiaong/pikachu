package com.huiaong.pikachu.item.cart.model;

import com.huiaong.pikachu.item.goods.model.PikaGoods;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaCartItem extends PikaGoods {

    private Integer quantity;
}
