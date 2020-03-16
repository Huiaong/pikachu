package com.huiaong.pikachu.web;

import com.huiaong.pikachu.web.core.PikachuCoreWebConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        PikachuCoreWebConfiguration.class,
})
@Configuration
public class PikachuWebConfiguration {
}
