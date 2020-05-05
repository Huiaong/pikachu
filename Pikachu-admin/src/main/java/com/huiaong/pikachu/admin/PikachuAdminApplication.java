package com.huiaong.pikachu.admin;

import com.huiaong.pikachu.admin.interceptor.PikachuAdminInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PikachuAdminApplication implements WebMvcConfigurer {

    @Value("${license.path:classpath}")
    private String licensePath;

    @Value("${license.path:classpath}")
    private String unLicensePath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PikachuAdminInterceptor()) // 添加拦截器
                .addPathPatterns("/**") // 添加拦截路径
                .excludePathPatterns("/hello")// 添加排除拦截路径
                .order(0);//执行顺序
    }

    public static void main(String[] args) {
        SpringApplication.run(PikachuAdminApplication.class, args);
    }

}
