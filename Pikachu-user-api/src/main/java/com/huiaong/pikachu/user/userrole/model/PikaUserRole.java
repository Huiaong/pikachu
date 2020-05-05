package com.huiaong.pikachu.user.userrole.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaUserRole extends PikaBaseBean {
    private Long userId;

    private Long roleId;
}
