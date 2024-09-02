package com.easypan;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author c.w
 * @className EsayPanApplication
 * @description run class
 * @date 2024/04/25
 **/
@EnableAsync
@SpringBootApplication(scanBasePackageClasses = IRun.class, scanBasePackages = {"utils"})
@MapperScan(basePackages = "com.easypan.mappers")
@EnableTransactionManagement
@EnableScheduling
// Spring redis session共享
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30 * 60)
public class EasyPanApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyPanApplication.class);
    }
}


