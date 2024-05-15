package com.easypan.entity.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @className AppConfig 
 * @description  
 * @author c.w
 * @date 2024/05/08
**/
@Component("appConfig")
public class AppConfig {
    @Value("${spring.mail.username:}")
    public String sendUserName;
}


