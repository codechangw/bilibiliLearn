package com.easypan.entity.enums;


import lombok.Getter;

/**
 * FolderTypeEnums
 *
 * @author c.w
 * @date 2024/08/30
 **/
@Getter
public enum FolderTypeEnums {
    FILE(0, "文件"),
    FOLDER(1, "目录");
    private Integer type;
    private String code;

    FolderTypeEnums(int type, String code) {
        this.type = type;
        this.code = code;
    }
}

