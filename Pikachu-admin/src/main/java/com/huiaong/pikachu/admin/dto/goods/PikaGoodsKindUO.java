package com.huiaong.pikachu.admin.dto.goods;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
public class PikaGoodsKindUO {

    @NotNull(message = "种类id不能为空")
    private Long id;

    @NotEmpty(message = "种类名称不能为空")
    private String nickName;

    @NotEmpty(message = "种类图片不能为空")
    private String imgPath;
}
