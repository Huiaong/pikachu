package com.huiaong.pikachu.common.base.model;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PikaBaseBean implements Serializable {

    private Long id;

    private Long createId;

    private LocalDateTime createdAt;

    private Long updatedId;

    private LocalDateTime updatedAt;

    private Integer delFlag;

}
