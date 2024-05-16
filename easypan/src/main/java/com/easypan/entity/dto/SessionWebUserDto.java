package com.easypan.entity.dto;


import lombok.Data;

/**
 * @className SessionWebUserDto
 * @description  
 * @author c.w
 * @date 2024/05/15
**/
@Data
public class SessionWebUserDto {
    private String nickName;
    private String userId;
    private Boolean isAdmin;
    private String avatar;
    //private
}


