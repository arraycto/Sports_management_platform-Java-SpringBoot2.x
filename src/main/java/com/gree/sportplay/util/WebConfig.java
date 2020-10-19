package com.gree.sportplay.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//全局配置类 配置跨域请求
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //允许访问我们后端的所有路径
                .allowedOrigins("http://localhost:8080", "null") //跨域请求的来源，允许访问的前端端口要配置为8080
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") //允许跨域的方法
                .maxAge(3600)  //最大响应时间
                .allowCredentials(true); //允许携带想tocken的东西
    }
}