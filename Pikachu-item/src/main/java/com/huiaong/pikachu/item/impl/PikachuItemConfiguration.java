package com.huiaong.pikachu.item.impl;

import com.huiaong.pikachu.item.impl.config.PikaItemRedisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PikaItemRedisConfig.class})
public class PikachuItemConfiguration {
}
