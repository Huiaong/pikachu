package com.huiaong.pikachu.trade.impl;


import com.huiaong.pikachu.trade.impl.order.config.PikaPurchaseOrderMQConfig;
import com.huiaong.pikachu.trade.impl.order.config.PikaPurchaseOrderRedisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PikaPurchaseOrderMQConfig.class, PikaPurchaseOrderRedisConfig.class})
public class PikachuTradeConfiguration {

}
