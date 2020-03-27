package com.huiaong.pikachu.article.impl;

import com.huiaong.pikachu.article.impl.config.PikaArticleRedisConfig;
import com.huiaong.pikachu.article.impl.config.PikaArticleRedissonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PikaArticleRedisConfig.class, PikaArticleRedissonConfig.class})
public class PikachuArticleConfiguration {
}
