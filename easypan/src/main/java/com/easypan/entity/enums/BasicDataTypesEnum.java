package com.easypan.entity.enums;


import java.util.ArrayList;
import java.util.List;

/**
 * @author c.w
 * @className BasicDataTypesEnum
 * @description
 * @date 2024/05/10
 **/
public enum BasicDataTypesEnum {
    STRING("java.lang.String"),
    INTEGER("java.lang.Integer"),
    LONG("java.lang.Long");
    private String dataType;
    BasicDataTypesEnum(String dataType) {
        this.dataType = dataType;
    }
    public String getDataType() {
        return dataType;
    }
    public static List<String> getAllDataTypes() {
        List<String> dataTypes = new ArrayList<>();
        for (BasicDataTypesEnum type : BasicDataTypesEnum.values()) {
            dataTypes.add(type.getDataType());
        }
        return dataTypes;
    }
}


