package com.huiaong.pikachu.admin.QO.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PikaRegisterQO implements Serializable {

    @NotBlank
    private String loginName;

    @NotBlank
    private String password;

    @NotNull
    private Integer loginType;
}
