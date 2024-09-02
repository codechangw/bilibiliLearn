package com.easypan.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @className AdminAccountConfig 
 * @description  
 * @author c.w
 * @date 2024/05/15
**/
@Component
@Getter
public class AdminAccountConfig {
    @Value("${admin.emails:}")
    private String adminEmail;
}


