package com.huiaong.pikachu.trade.impl.order.config;

import com.huiaong.pikachu.trade.impl.order.callback.PikaTradeConfirmCallBackImpl;
import com.huiaong.pikachu.trade.impl.order.dao.PikaTradeMQResponseDao;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PikaPurchaseOrderMQConfig {

    @Bean
    public Queue purchaseOrderCreateQueue() {
        return new Queue("trade-purchase-create-queue", true, false, false);
    }

    @Bean
    public DirectExchange purchaseOrderCreateExchange() {
        return new DirectExchange("trade-purchase-create-exchange", true, false);
    }

    @Bean
    public Binding purchaseOrderCreateBinding() {
        return BindingBuilder.bind(purchaseOrderCreateQueue()).to(purchaseOrderCreateExchange()).with("trade-purchase-create-key");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory factory, PikaTradeMQResponseDao tradeMQResponseDao) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setConfirmCallback(new PikaTradeConfirmCallBackImpl(tradeMQResponseDao));
        return rabbitTemplate;
    }

}
