package com.huiaong.pikachu.resource.impl;

import com.huiaong.pikachu.resource.impl.properties.PikaAlioosProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PikaAlioosProperties.class})
public class PikachuResourceConfiguration {
}
