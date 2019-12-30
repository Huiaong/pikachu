package com.huiaong.pikachu.trade.base.module;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PikaBaseBean implements Serializable {

    private Long id;

    private Long createId;

    private Date createAt;

    private Date updatedAt;

}
