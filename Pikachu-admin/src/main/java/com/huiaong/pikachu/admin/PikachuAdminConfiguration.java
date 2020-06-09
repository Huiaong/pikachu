package com.huiaong.pikachu.admin;

import com.huiaong.pikachu.admin.interceptor.PikachuAuthInterceptor;
import com.huiaong.pikachu.web.core.PikachuCoreWebConfiguration;
import com.huiaong.pikachu.web.core.interceptor.PikachuLoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@Import({
        PikachuCoreWebConfiguration.class,
})
@Configuration
public class PikachuAdminConfiguration implements WebMvcConfigurer {

    @Value("${license.path}")
    private List<String> licensePath;

    @Value("${un-license.path}")
    private List<String> unLicensePath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pikachuLoginInterceptor()) // 添加拦截器
                .addPathPatterns(licensePath) // 添加拦截路径
                .excludePathPatterns(unLicensePath)// 添加排除拦截路径
                .order(1);//执行顺序
        registry.addInterceptor(pikachuAuthInterceptor()) // 添加拦截器
                .addPathPatterns(licensePath) // 添加拦截路径
                .excludePathPatterns(unLicensePath)// 添加排除拦截路径
                .order(2);//执行顺序
        log.info("interceptor has been load, licensePath:{}, unLicensePath:{}", licensePath, unLicensePath);
    }

    @Bean
    public PikachuAuthInterceptor pikachuAuthInterceptor() {
        return new PikachuAuthInterceptor();
    }

    @Bean
    public PikachuLoginInterceptor pikachuLoginInterceptor() {
        return new PikachuLoginInterceptor();
    }
}
