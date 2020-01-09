package com.huiaong.pikachu.receiver;

import com.huiaong.pikachu.receiver.trade.config.PikaPurchaseOrderMQConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        PikaPurchaseOrderMQConfig.class
})
@Configuration
public class PikachuReceiverConfiguration {
}
