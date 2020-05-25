package com.huiaong.pikachu.admin.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class PikaUserInfoVO implements Serializable {

    private Long id;

    private String name;

    private String email;

    private String mobile;

    private String portrait;
}
