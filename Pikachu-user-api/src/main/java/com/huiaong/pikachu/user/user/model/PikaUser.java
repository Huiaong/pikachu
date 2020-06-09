package com.huiaong.pikachu.user.user.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import com.huiaong.pikachu.user.userrole.model.PikaUserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaUser extends PikaBaseBean {

    private String name;

    private String email;

    private String mobile;

    private String password;

    /**
     * @see com.huiaong.pikachu.user.user.enums.PikaUserType
     */
    private Integer type;

    /**
     * @see com.huiaong.pikachu.user.user.enums.PikaUserStatus
     */
    private Integer status;

    private List<PikaUserRole> roleIds;

    private String portrait;

}
