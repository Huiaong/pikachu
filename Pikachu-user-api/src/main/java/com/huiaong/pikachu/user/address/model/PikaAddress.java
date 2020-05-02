package com.huiaong.pikachu.user.address.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PikaAddress extends PikaBaseBean {

    private Integer pid;

    private String name;

    private Integer level;

    private String pinyin;

    private String englishName;

    private String unicodeCode;
}
