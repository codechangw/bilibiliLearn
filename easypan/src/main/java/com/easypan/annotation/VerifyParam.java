package com.easypan.annotation;


import com.easypan.entity.enums.VerifyRegexEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * VerifyParam  
 * @author c.w
 * @date 2024/05/09 
**/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
public @interface VerifyParam {
    int min() default -1;
    int max() default -1;

    /**
     * 是否必须 default true
     * @return boolean
     */
    boolean required() default true;

    /**
     * 正则
     */
    VerifyRegexEnum regex() default VerifyRegexEnum.NO;
}

