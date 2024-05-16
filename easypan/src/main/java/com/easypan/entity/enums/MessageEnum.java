package com.easypan.entity.enums;


import lombok.Getter;

/**
 * MessageEnum
 *
 * @author c.w
 * @date 2024/05/14
 **/
@Getter
public enum MessageEnum {
    CHECK_CODE_KEY_EMAIL("", "",""),

    EMAIL_SEND_ERROR("email send error", "邮件发送失败","EMAIL_SEND_ERROR"),
    EMAIL_SEND_SUCCESS("email send success", "邮件发送成功","EMAIL_SEND_SUCCESS"),

    CHECK_CODE("check code","","CHECK_CODE"),

    CODE_ERROR("image code error","验证码错误","CODE_ERROR"),
    CODE_IMG_ERROR("image code error","图片验证码错误","IMG_CODE_ERROR"),
    CODE_EMAIL_ERROR("email code error","邮箱验证码错误","EMAIL_CODE_ERROR"),

    CODE_INVALIDATED("code invalidated","验证码失效","CODE_INVALIDATED"),
    CODE_IMAGE_INVALIDATED("code image invalidated","图片验证码失效","CODE_IMAGE_INVALIDATED"),
    CODE_EMAIL_INVALIDATED("code email invalidated","邮箱验证码失效","CODE_EMAIL_INVALIDATED"),

    // 注册
    EMAIL_EXITS("email_exits","邮箱已存在","EMAIL_EXITS"),
    NICKNAME_EXITS("nickname_exits","昵称已存在","NICKNAME_EXITS"),

    // 登录
    ACCOUNT_DISABLED("account has bean disabled","账号已被禁用","ACCOUNT_DISABLED"),
    ACCOUNT_DOES_NOT_EXISTS("Account does not exist","账号不存在","ACCOUNT_DOES_NOT_EXISTS"),
    WRONG_PASSWORD("wrong password ","密码错误","WRONG_PASSWORD"),

    END("","","");

    private final String en;
    private final String cn;
    private final String code;

    MessageEnum(String en, String cn,String code) {
        this.en = en;
        this.cn = cn;
        this.code = code;
    }
}

