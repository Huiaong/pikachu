package com.huiaong.pikachu.user.auth.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaRole  extends PikaBaseBean {

    private String name;

    private String value;

}
