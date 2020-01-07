package com.huiaong.pikachu.trade.order.event;

import com.huiaong.pikachu.common.base.producer.Event;
import com.huiaong.pikachu.common.base.producer.MessageDto;

import java.io.Serializable;

public class PikaPurchaseOrderEvent extends Event implements Serializable {

    public PikaPurchaseOrderEvent(String messageId, String exchange, String routingKey, MessageDto event) {
        super(messageId, exchange, routingKey, event);
    }
}
