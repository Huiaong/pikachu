package com.huiaong.pikachu.item.cart.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaCart extends PikaBaseBean {

    private Long userId;

    private List<PikaCartItem> items;

}
