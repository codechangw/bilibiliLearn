package com.easypan.entity.enums;


import lombok.Getter;

/**
 * @className UserStatusEnum 
 * @description  
 * @author c.w
 * @date 2024/05/11
**/
@Getter
public enum UserStatusEnum {
    ENABLE(1),
    DISABLE(0);
    private Integer status;
    UserStatusEnum(int status) {
        this.status = status;
    }
}


