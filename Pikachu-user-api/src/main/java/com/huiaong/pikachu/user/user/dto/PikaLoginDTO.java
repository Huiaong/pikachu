package com.huiaong.pikachu.user.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PikaLoginDTO implements Serializable {

    private Long id;

    private String token;
}
