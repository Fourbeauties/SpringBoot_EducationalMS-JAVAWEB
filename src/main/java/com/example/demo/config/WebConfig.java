package com.example.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean requestContextFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestContextFilter());
        registration.addUrlPatterns("/*"); // 匹配所有的URL请求
        return registration;
    }


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webapp/WEB-INF/**")
                .addResourceLocations("classpath:/webapp/WEB-INF/") // 如果webapp在类路径下
                // 或者
//                .addResourceLocations("file:/path/to/your/webapp/") // 如果webapp在文件系统中
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));// 可选，设置缓存策略

    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); // 设置全局日期格式
        // 或者使用Java 8的日期时间API
        // objectMapper.registerModule(new JavaTimeModule());
        // objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 添加到转换器列表
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }

}
