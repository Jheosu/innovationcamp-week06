//package com.example.scheduler.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class WebMvcConfig implements WebMvcConfigurer {
//    private final long MAX_AGE_SECS = 3600;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedMethods("*")
//                .allowedHeaders("Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
//                .exposedHeaders("Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
//                .allowCredentials(true)
//                .maxAge(MAX_AGE_SECS);
//    }
//}
