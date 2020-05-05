package com.huiaong.pikachu.user.impl;

import com.huiaong.pikachu.user.impl.config.PikaUserRedisTemplateConfig;
import com.huiaong.pikachu.user.impl.config.PikaUserRedissonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PikaUserRedissonConfig.class, PikaUserRedisTemplateConfig.class})
public class PikachuUserConfiguration {
}
