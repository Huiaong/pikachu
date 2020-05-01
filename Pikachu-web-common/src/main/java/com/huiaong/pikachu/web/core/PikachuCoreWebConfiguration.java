package com.huiaong.pikachu.web.core;

import com.huiaong.pikachu.web.core.config.Swagger2;
import com.huiaong.pikachu.web.core.interceptors.PikaInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan("com.huiaong.pikachu.web.core")
@Configuration
@Import({Swagger2.class})
public class PikachuCoreWebConfiguration implements WebMvcConfigurer{

    @Value("${license.path:classpath}")
    private String licensePath;

    @Value("${license.path:classpath}")
    private String unLicensePath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PikaInterceptor()) // 添加拦截器
                .addPathPatterns("/**") // 添加拦截路径
                .excludePathPatterns("/hello")// 添加排除拦截路径
                .order(0);//执行顺序
    }
}
