package com.easypan.component.redis;


import com.easypan.constants.RedisKeyConstants;
import com.easypan.entity.dto.SysSettingDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author c.w
 * @className RedisComponent
 * @description
 * @date 2024/05/08
 **/
@Component("redisComponent")
public class RedisComponent {
    @Resource
    private RedisUtils<SysSettingDto> redisUtils;

    public SysSettingDto getSysSettingDto() {
        SysSettingDto sysSettingDto = redisUtils.get(RedisKeyConstants.REDIS_KEY_SYS_SETTING);
        if (sysSettingDto == null) {
            sysSettingDto = new SysSettingDto();
            redisUtils.set(RedisKeyConstants.REDIS_KEY_SYS_SETTING, sysSettingDto);
        }
        return sysSettingDto;
    }
    public boolean setSysSettingDto(SysSettingDto sysSettingDto) {
        return redisUtils.set(RedisKeyConstants.REDIS_KEY_SYS_SETTING, sysSettingDto);
    }
}


