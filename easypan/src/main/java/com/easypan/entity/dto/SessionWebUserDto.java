package com.easypan.entity.dto;


import lombok.Data;
import java.io.Serializable;


/**
 * @className SessionWebUserDto
 * @description  
 * @author c.w
 * @date 2024/05/15
**/
@Data
public class SessionWebUserDto implements Serializable {
    private String nickName;
    private String userId;
    private Boolean isAdmin;
    private String avatar;
}


