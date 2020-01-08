package com.huiaong.pikachu.common.base.producer;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Event extends PikaBaseBean {

    private String messageId;

    private String exchange;

    private String routingKey;

    private String content;
}
