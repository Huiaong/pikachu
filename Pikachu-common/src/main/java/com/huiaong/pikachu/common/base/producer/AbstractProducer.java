package com.huiaong.pikachu.common.base.producer;

import com.huiaong.pikachu.common.util.DateUtils;
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
        String content = event.getContent();
        String messageId = event.getMessageId();

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(messageId);
        this.send(exchange, routingKey, content, correlationData);
    }

    public void send(String exchange, String routingKey, String content) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString().replace("-", "") + DateUtils.formatDate(new Date(), "yyyyMMdd"));
        this.send(exchange, routingKey, content, correlationData);
    }

    public void send(String exchange, String routingKey, String content, CorrelationData correlationData) {
        log.info("MQ message:{} send to:{} with:{}", correlationData.getId(), exchange, routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, content, correlationData);
    }

}
