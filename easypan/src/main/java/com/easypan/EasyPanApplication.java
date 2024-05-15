package com.easypan;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @className EsayPanApplication 
 * @description run class 
 * @author c.w
 * @date 2024/04/25
**/
@EnableAsync
@SpringBootApplication(scanBasePackageClasses = IRun.class)
@MapperScan(basePackages = "com.easypan.mappers")
@EnableTransactionManagement
@EnableScheduling
public class EasyPanApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyPanApplication.class);
    }
}


