package com.easypan.constants;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author c.w
 * @className DateConstants
 * @description
 * @date 2024/04/29
 **/
public class DateConstants {
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer LENGTH_1 = 1;
    @Value("${email.code.valid.time}")
    public static Integer LENGTH_EMAIL_VALID_TIME;
    public static final Float LENGTH_05 = 0.5F;
    public static final Integer LENGTH_5 = 5;
    public static final Integer LENGTH_10 = 10;
    public static final Integer LENGTH_15 = 15;
    public static final Long MB = 1024 * 1024L;
    public static final String A_TO_Z = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String A_TO_Z2 = "ABCDEFGHJKMNPQRSTUVWXYZ";
    public static final String a_TO_z = "abcdefghijklmnopqrstuvwxyz";
    public static final String a_TO_z2 = "abcdefghjkmnpqrstuvwxyz";
    public static final String STR_1_TO_9 = "123456789";
    public static final String STR_2_TO_9 = "23456789";

    /* 时间 开始 */
    public static final Long SECOND_ONE = 1L;
    public static final Long SECOND_HALF_MINUTE = 30L;
    public static final Long SECOND_MINUTE = 60L;
    public static final Long SECOND_HOUR = 60L * 60;
    public static final Long SECOND_DAY = 60L * 60 * 24;
    public static final Long MINUTE_ONE = 1L;
    public static final Long MINUTE_HALF_HOUR = 30L;
    public static final Long MINUTE_HOUR = 60L;
    public static final Integer HOUR_ONE = 1;
    public static final Integer HOUR_12 = 12;
    public static final Integer HOUR_DAY = 24;
    /* 时间 结束*/
    /* 内存单位 */
    public static final Long B = 1L;
    public static final Long KB_B = 1024L;
    public static final Long MB_B = 1024 * 1024L;
    public static final Long GB_B = 1024 * 1024 * 1024L;
    public static final Long TB_B = 1024 * 1024 * 1024 * 1024L;

}



