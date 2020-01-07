package com.huiaong.pikachu.admin;

import com.huiaong.pikachu.admin.order.config.PikaPurchaseOrderMQConfig;
import com.huiaong.pikachu.web.core.PikachuCoreWebConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        PikachuCoreWebConfiguration.class,
        PikaPurchaseOrderMQConfig.class
})
@Configuration
public class PikachuAdminConfiguration {
}
