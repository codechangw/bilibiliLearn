package com.easypan.entity.enums;


import lombok.Getter;
import lombok.Setter;

/**
 * @className UserStatusEnum 
 * @description  
 * @author c.w
 * @date 2024/05/11
**/
@Getter
public enum UserStatusEnum {

    DISABLE(0, "禁用"),
    ENABLE(1, "启用");

    private Integer status;
    @Setter
    private String desc;

    UserStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static UserStatusEnum getByStatus(Integer status) {
        for (UserStatusEnum item : UserStatusEnum.values()) {
            if (item.getStatus().equals(status)) {
                return item;
            }
        }
        return null;
    }

}


