package com.huiaong.pikachu.common.base.producer;

import com.huiaong.pikachu.common.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

@Slf4j
public abstract class AbstractProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Event event) {
        String exchange = event.getExchange();
        String routingKey = event.getRoutingKey();
        MessageDto messageDto = event.getEvent();
        String messageId = event.getMessageId();
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(messageId);
        this.send(exchange, routingKey, messageDto, correlationData);
    }

    public void send(String exchange, String routingKey, MessageDto messageDto) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString().replace("-", "") + DateUtils.formatDate(new Date(), "yyyyMMdd"));
        this.send(exchange, routingKey, messageDto, correlationData);
    }

    public void send(String exchange, String routingKey, MessageDto messageDto, CorrelationData correlationData) {
        log.info("MQ message:{} send to:{} with:{}", correlationData.getId(), exchange, routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, messageDto, correlationData);
    }

}
