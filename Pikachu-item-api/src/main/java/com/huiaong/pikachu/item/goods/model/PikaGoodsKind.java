package com.huiaong.pikachu.item.goods.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaGoodsKind extends PikaBaseBean {
    private Long goodsId;

    private String name;

    private String nickName;

    private String imgPath;

}
