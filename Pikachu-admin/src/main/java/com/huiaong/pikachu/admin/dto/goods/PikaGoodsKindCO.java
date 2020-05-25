package com.huiaong.pikachu.admin.dto.goods;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PikaGoodsKindCO {

    @NotEmpty(message = "种类名称不能为空")
    private String nickName;

    @NotEmpty(message = "种类图片不能为空")
    private String imgPath;
}
