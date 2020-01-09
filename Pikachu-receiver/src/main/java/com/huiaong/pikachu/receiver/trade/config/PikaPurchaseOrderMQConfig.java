package com.huiaong.pikachu.receiver.trade.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PikaPurchaseOrderMQConfig {

    @Bean
    public Queue purchaseOrderCreateQueue() {
        return new Queue("trade-purchase-create-queue", true, false, false);
    }

    @Bean
    public FanoutExchange purchaseOrderCreateExchange() {
        return new FanoutExchange("trade-purchase-create-exchange");
    }

    @Bean
    public Binding purchaseOrderCreateBinding() {
        return BindingBuilder.bind(purchaseOrderCreateQueue()).to(purchaseOrderCreateExchange());
    }


}
