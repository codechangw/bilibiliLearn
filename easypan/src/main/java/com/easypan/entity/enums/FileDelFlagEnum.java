package com.easypan.entity.enums;


import lombok.Getter;

/**
 * @className FileDelFlagEnum 
 * @description 文件删除标记枚举
 * @author c.w
 * @date 2024/08/21
**/
@Getter
public enum FileDelFlagEnum {
    DEL(0, "删除"),
    RECYCLE (1, "回收站"),
    USING(2, "使用中");

    private Integer flag;
    private String desc;

    FileDelFlagEnum(Integer flag, String desc) {
        this.flag = flag;
        this.desc = desc;
    }
}


