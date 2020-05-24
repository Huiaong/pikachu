package com.huiaong.pikachu.resources.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class PikaGoodsPicture implements Serializable {

    private String fileName;

    private String fileAddr;

}
