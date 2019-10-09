package com.huiaong.pikachu.trade.sms.model;

import com.huiaong.pikachu.trade.sms.enums.NormalMessagePlatform;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NormalMessage implements Serializable {

    private Long id;

    private String message;

    /**
     * @see NormalMessagePlatform
     */
    private Integer platform;

    private Long sendTo;

    private Date createTime;

    private Date updateTime;

}
