package com.easypan.entity.dto;


import lombok.Data;

/**
 * @className UserSpaceDto 
 * @description  
 * @author c.w
 * @date 2024/05/15
**/
@Data
public class UserSpaceDto {
    /**
     * 用户总空间
     */
    private Long totalSpace;
    /**
     * 已使用空间
     */
    private Long useSpace;
}


