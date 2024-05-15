package com.easypan.entity.enums;


import lombok.Getter;
/**
 * VerifyRegexEnum
 * 正则表达式枚举类
 * @author c.w 
 * @date 2024/05/09 
**/
@Getter
public enum VerifyRegexEnum {
    NO("","不校验"),
    IP("^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$","IP地址"),
    POSITIVE_INTEGER("^[0-9]*[1-9][0-9]*$","正整数"),
    EMAIL("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$","邮箱地址"),
    CHARACTER_NUMBER_UNDERLINE("^[0-9a-zA-Z_]{1,}$","字母数字下划线"),
    CHARACTER_NUMBER("^[0-9a-zA-Z]{1,}$","字母数字"),
    NUMBER("^[0-9]{1,}$","字母数字"),
    NUMBER_5("^[0-9]{5,5}$","字母数字"),
    CHECK_CODE("^[2-9a-hj-km-np-zA-HJ-KM-NP-Z]{5,5}$","验证码"),
    CHECK_CODE_ENUM("^[ABCDEFGHJKMNPQRSTUVWXYZ23456789]{5,5}$","验证码"),
    PASSWORD("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9!@#$%^&*()\\\\s]{8,20}$","密码"),
    END("","");
    private String regex;
    private String desc;
    VerifyRegexEnum(String regex, String desc) {
        this.regex = regex;
        this.desc = desc;
    }
}

