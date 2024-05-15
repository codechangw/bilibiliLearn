package com.easypan.annotation;


import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * GlobalInterceptor  
 * @author c.w
 * @date 2024/05/09 
**/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface GlobalInterceptor {
    /**
     * 是否需要校验参数
     * @return
     */
    boolean checkParams() default false;

    /**
     * 是否需要登录
     * @return
     */
    boolean checkLogin() default false;

}

