package com.easypan.component.redis;


import com.easypan.config.AppConfig;
import com.easypan.constants.DateConstants;
import com.easypan.constants.RedisKeyConstants;
import com.easypan.entity.dto.SessionWebUserDto;
import com.easypan.entity.dto.SysSettingDto;
import com.easypan.entity.dto.UserSpaceDto;
import com.easypan.utils.StringTools;
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
    private RedisUtils redisUtils;
    @Resource
    private AppConfig appConfig;

    public SysSettingDto getSysSettingDto() {
        SysSettingDto sysSettingDto = (SysSettingDto) redisUtils.get(RedisKeyConstants.REDIS_KEY_SYS_SETTING);
        if (sysSettingDto == null) {
            sysSettingDto = new SysSettingDto();
            redisUtils.set(RedisKeyConstants.REDIS_KEY_SYS_SETTING, sysSettingDto);
        }
        return sysSettingDto;
    }

    public boolean setSysSettingDto(SysSettingDto sysSettingDto) {
        return redisUtils.set(RedisKeyConstants.REDIS_KEY_SYS_SETTING, sysSettingDto);
    }

    public UserSpaceDto getUseSpace(String userId) {
        return (UserSpaceDto) redisUtils.get(StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_USER_SPACE, userId));
    }

    public boolean setUseSpace(String userId, UserSpaceDto userSpaceDto) {
        return redisUtils.set(StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_USER_SPACE, userId), userSpaceDto);
    }

    public Long getFileTempSize(String userId, String fileId) {
        Object tmpObj = redisUtils.get(StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_USER_TEMP_SIZE, userId, fileId));
        if (tmpObj == null) {
            return 0L;
        }
        if (tmpObj instanceof Integer) {
            return ((Integer) tmpObj).longValue();
        }
        if (tmpObj instanceof Long) {
            return (Long) tmpObj;
        }
        return 0L;
    }

    /**
     * 临时空间增加文件使用大小
     * @param userId
     * @param fileId
     * @param fileSize  增加的文件大小
     */
    public void setFileTempSize(String userId, String fileId, Long fileSize) {
        Long fileTempSize = this.getFileTempSize(userId, fileId);
        redisUtils.set(StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_USER_TEMP_SIZE, userId, fileId), fileTempSize + fileSize, DateConstants.SECOND_HOUR);
    }

    /**
     * 删除用户某文件临时空间
     * @param userId
     * @param fileId
     */
    public void delFileTempSize(String userId,String fileId){
        redisUtils.delete(StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_USER_TEMP_SIZE, userId, fileId));
    }

}


