package com.huiaong.pikachu.item.impl;

import com.huiaong.pikachu.item.impl.config.PikaItemRedisTemplateConfig;
import com.huiaong.pikachu.item.impl.config.PikaItemRedissonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PikaItemRedissonConfig.class, PikaItemRedisTemplateConfig.class})
public class PikachuItemConfiguration {
}
