package com.huiaong.pikachu.common.base.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PikaBaseBean implements Serializable {

    private Long id;

    private Long createId;

    private Date createdAt;

    private Date updatedAt;

}
