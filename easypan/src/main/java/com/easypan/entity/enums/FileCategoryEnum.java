package com.easypan.entity.enums;


import com.easypan.utils.StringTools;
import lombok.Getter;

/**
 * @author c.w
 * @className FileCategoryEnum
 * @description 文件类型枚举
 * @date 2024/08/21
 **/
@Getter
public enum FileCategoryEnum {
    VIDEO(1, "video", "视频"),
    MUSIC(2, "music", "音频"),
    IMAGE(3, "image", "图片"),
    DOC(4, "doc", "文档"),
    OTHERS(5, "others", "其他"),
    ALL(null, "all", "全部"),
    ERROR(500, "error", "错误"),
    ;

    private Integer category;
    private String code;
    private String desc;

    FileCategoryEnum(Integer category, String code, String desc) {
        this.category = category;
        this.code = code;
        this.desc = desc;
    }

    public static FileCategoryEnum getByCode(String code) {
        if (!StringTools.isNotNull(code)) {
            return FileCategoryEnum.ERROR;
        }
        for (FileCategoryEnum value : FileCategoryEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return FileCategoryEnum.ERROR;
    }

}


