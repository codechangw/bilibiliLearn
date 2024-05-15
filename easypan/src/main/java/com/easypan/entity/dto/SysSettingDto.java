package com.easypan.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @className SysSettingDto 
 * @description  
 * @author c.w
 * @date 2024/05/08
**/
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SysSettingDto implements Serializable {
    private String registerMailTitle = "邮箱验证码";

    private String registerMailContent = "邮箱验证码是：%s ,15分钟有效";

    private Integer userInitSpace = 5;

}


