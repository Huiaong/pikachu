package com.huiaong.pikachu.admin.VO.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class PikaLoginVO implements Serializable {

    private Long id;

    private String token;
}