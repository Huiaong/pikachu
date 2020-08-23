package com.huiaong.pikachu.user.user.dto;

import com.huiaong.pikachu.common.base.model.PikaBaseUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PikaLoginUser implements Serializable, PikaBaseUser {

    private Long id;

    private String name;

    private String token;

    private List<String> roles;
}
