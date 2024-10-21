package com.easypan.entity.dto;

import com.easypan.constants.DateConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @className SysSettingDto
 * @description
 * @date 2024/05/08
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SysSettingDto implements Serializable {
    private Integer emailCodeValidTime = 5;
    private String registerMailTitle = "邮箱验证码";
    private String registerMailContent = "邮箱验证码是：%s ," + emailCodeValidTime + "分钟有效";
    private Long userInitSpace = 5 * DateConstants.GB_B;
}


