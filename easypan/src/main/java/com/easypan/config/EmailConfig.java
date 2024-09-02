package com.easypan.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @className EmailConfig
 * @description  
 * @author c.w
 * @date 2024/05/08
**/
@Component("emailConfig")
@Getter
public class EmailConfig {
    @Value("${spring.mail.username:}")
    private String sendUserName;
    @Value("${email.code.valid.time:}")
    private Integer emailCodeValidTime;
}


