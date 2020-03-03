package com.huiaong.pikachu.article.impl;

import com.huiaong.pikachu.article.impl.config.PikaArticleRedisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PikaArticleRedisConfig.class})
public class PikachuArticleConfiguration {
}
