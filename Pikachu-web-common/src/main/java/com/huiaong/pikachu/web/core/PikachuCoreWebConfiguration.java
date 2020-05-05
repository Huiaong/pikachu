package com.huiaong.pikachu.web.core;

import com.huiaong.pikachu.web.core.config.Swagger2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan("com.huiaong.pikachu.web.core")
@Configuration
@Import({Swagger2.class})
public class PikachuCoreWebConfiguration {
}
