package com.example.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @Author fmrx
 * @Date 2020/3/24
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public AutoIdempotentInterceptor autoIdempotentInterceptor (){
        return new AutoIdempotentInterceptor();
    }
//    @Resource
//    private AutoIdempotentInterceptor autoIdempotentInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor());
        super.addInterceptors(registry);
    }
}
