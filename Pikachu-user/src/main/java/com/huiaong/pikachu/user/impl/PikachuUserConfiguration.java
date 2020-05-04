package com.huiaong.pikachu.user.impl;

import com.huiaong.pikachu.user.impl.config.PikaUserRedisTemplateConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PikaUserRedisTemplateConfig.class})
public class PikachuUserConfiguration {
}
