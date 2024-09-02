package com.easypan.constants;


/**
 * @author c.w
 * @className RedisKeyConstants
 * @description
 * @date 2024/05/11
 **/
public class RedisKeyConstants {
    /**
     * 配置
     */
    public static final String REDIS_KEY_SYS_SETTING = "easy:syssetting:";
    /**
     * 邮箱验证码
     */
    public static final String REDIS_KEY_EMAIL_CODE = "code:email:";
    /**
     * 图片验证码
     */
    public static final String REDIS_KEY_CODE_IMAGE = "code:image:";
    /**
     * 图片验证码,发送邮件前
     */
    public static final String REDIS_KEY_CODE_IMAGE_EMAIL = "code:image:email:";
    /**
     * 用户总空间
     */
    public static final String REDIS_KEY_TOTAL_SPACE = "space:total:";
    /**
     * 用户已使用空间
     */
    public static final String REDIS_KEY_USE_SPACE = "space:use:";
    /**
     * 用户总空间和已使用空间
     */
    public static final String REDIS_KEY_USER_SPACE = "space:user:";
    /**
     * 临时文件大小
     */
    public static final String REDIS_KEY_USER_TEMP_SIZE = "space:temp:";
    /**
     * 临时文件路径
     */
    public static final String REDIS_KEY_USER_TEMP_FILE_PATH = "temp:filePath:";

    public static final String REDIS_BLOCK_USER_SPACE = "userSpaceBlock:";
}


