package com.huiaong.pikachu.admin.QO.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PikaLoginQO implements Serializable {

    @NotBlank
    private String loginName;

    @NotBlank
    private String password;

}
