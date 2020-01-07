package com.huiaong.pikachu.common.base.producer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {

    private String messageId;

    private String exchange;

    private String routingKey;

    private MessageDto event;
}
