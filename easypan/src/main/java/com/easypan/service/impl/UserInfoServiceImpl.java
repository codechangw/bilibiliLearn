package com.easypan.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.easypan.component.redis.RedisComponent;
import com.easypan.component.redis.RedisUtils;
import com.easypan.config.AppConfig;
import com.easypan.constants.OtherConstants;
import com.easypan.constants.RedisKeyConstants;
import com.easypan.config.AdminAccountConfig;
import com.easypan.constants.DateConstants;
import com.easypan.entity.dto.SessionWebUserDto;
import com.easypan.entity.dto.SysSettingDto;
import com.easypan.entity.dto.UserSpaceDto;
import com.easypan.entity.enums.MessageEnum;
import com.easypan.entity.enums.UserStatusEnum;
import com.easypan.exception.BusinessException;
import com.easypan.service.EmailCodeService;
import com.easypan.service.FileInfoService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.easypan.entity.enums.PageSize;
import com.easypan.entity.query.UserInfoQuery;
import com.easypan.entity.po.UserInfo;
import com.easypan.entity.vo.PaginationResultVO;
import com.easypan.entity.query.SimplePage;
import com.easypan.mappers.UserInfoMapper;
import com.easypan.service.UserInfoService;
import com.easypan.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户信息 业务接口实现
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;
    @Resource
    private EmailCodeService emailCodeService;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private RedisComponent redisComponent;
    @Resource
    private AdminAccountConfig adminAccountConfig;
    @Autowired
    private AppConfig appConfig;

    /**
     * 根据UserId删除
     */
    @Override
    public Integer deleteUserInfoByUserId(String userId) {
        return this.userInfoMapper.deleteByUserId(userId);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public List<UserInfo> findListByParam(UserInfoQuery param) {
        return this.userInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(UserInfoQuery param) {
        return this.userInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<UserInfo> findListByPage(UserInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<UserInfo> list = this.findListByParam(param);
        PaginationResultVO<UserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(UserInfo bean) {
        return this.userInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<UserInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.userInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<UserInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.userInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(UserInfo bean, UserInfoQuery param) {
        StringTools.checkParam(param);
        return this.userInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(UserInfoQuery param) {
        StringTools.checkParam(param);
        return this.userInfoMapper.deleteByParam(param);
    }

    /**
     * 根据UserId获取对象
     */
    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        return this.userInfoMapper.selectByUserId(userId);
    }

    /**
     * 根据UserId修改
     */
    @Override
    public Integer updateUserInfoByUserId(UserInfo bean, String userId) {
        return this.userInfoMapper.updateByUserId(bean, userId);
    }

    /**
     * 根据Email获取对象
     */
    @Override
    public UserInfo getUserInfoByEmail(String email) {
        return this.userInfoMapper.selectByEmail(email);
    }

    /**
     * 根据Email删除
     */
    @Override
    public Integer deleteUserInfoByEmail(String email) {
        return this.userInfoMapper.deleteByEmail(email);
    }

    /**
     * 根据Email修改
     */
    @Override
    public Integer updateUserInfoByEmail(UserInfo bean, String email) {
        return this.userInfoMapper.updateByEmail(bean, email);
    }

    /**
     * 根据NickName获取对象
     */
    @Override
    public UserInfo getUserInfoByNickName(String nickName) {
        return this.userInfoMapper.selectByNickName(nickName);
    }

    /**
     * 根据NickName修改
     */
    @Override
    public Integer updateUserInfoByNickName(UserInfo bean, String nickName) {
        return this.userInfoMapper.updateByNickName(bean, nickName);
    }

    /**
     * 根据NickName删除
     */
    @Override
    public Integer deleteUserInfoByNickName(String nickName) {
        return this.userInfoMapper.deleteByNickName(nickName);
    }

    /**
     * 根据QqOpenId获取对象
     */
    @Override
    public UserInfo getUserInfoByQqOpenId(String qqOpenId) {
        return this.userInfoMapper.selectByQqOpenId(qqOpenId);
    }

    /**
     * 根据QqOpenId修改
     */
    @Override
    public Integer updateUserInfoByQqOpenId(UserInfo bean, String qqOpenId) {
        return this.userInfoMapper.updateByQqOpenId(bean, qqOpenId);
    }

    /**
     * 根据QqOpenId删除
     */
    @Override
    public Integer deleteUserInfoByQqOpenId(String qqOpenId) {
        return this.userInfoMapper.deleteByQqOpenId(qqOpenId);
    }

    /**
     * 注册
     *
     * @param email     email
     * @param nickName  nickName
     * @param password  password
     * @param emailCode emailCode
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String email, String nickName, String password, String emailCode) {
        UserInfo userInfo = this.userInfoMapper.selectByEmail(email);
        if (userInfo != null) {
            throw new BusinessException(MessageEnum.EMAIL_EXITS.getCn());
        }
        UserInfo nickNameUser = this.userInfoMapper.selectByNickName(nickName);
        if (nickNameUser != null) {
            throw new BusinessException(MessageEnum.NICKNAME_EXITS.getCn());
        }
        //  校验邮箱验证码
        emailCodeService.checkCode(email, emailCode);
        //  用户信息
        String userId = StringTools.getRandomNumber(DateConstants.LENGTH_10);
        userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setEmail(email);
        userInfo.setNickName(nickName);
        userInfo.setPassword(StringTools.encodeMD5(password));
        userInfo.setJoinTime(new Date());
        userInfo.setStatus(UserStatusEnum.ENABLE.getStatus());
        //  初始化用户空间
        userInfo.setUseSpace(0L);
        SysSettingDto sysSettingDto = redisComponent.getSysSettingDto();
        userInfo.setTotalSpace(sysSettingDto.getUserInitSpace() * DateConstants.MB);
        //  初始化用户空间-redis
        UserSpaceDto userSpaceDto = new UserSpaceDto();
        userSpaceDto.setUseSpace(0L);
        userSpaceDto.setTotalSpace(sysSettingDto.getUserInitSpace() * DateConstants.MB);
        //  保存
        this.userInfoMapper.insert(userInfo);
        redisUtils.set(StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_USER_SPACE, userId), userSpaceDto, DateConstants.SECOND_HOUR * 6);
        //  异步创建用户目录
        this.createUserFolder(userId);
    }

    /**
     * 登录
     *
     * @param email    email
     * @param password pwd
     * @return SessionWebUserDto
     */
    @Override
    public SessionWebUserDto login(String email, String password) {
        UserInfo userInfo = userInfoMapper.selectByEmail(email);
        //  账号不存在
        if (userInfo == null) {
            throw new BusinessException(MessageEnum.ACCOUNT_DOES_NOT_EXISTS.getCn());
        }
        //  密码错误
        if (!Objects.equals(password, userInfo.getPassword())) {
            throw new BusinessException(MessageEnum.WRONG_PASSWORD.getCn());
        }
        //  账号被禁用
        if (userInfo.getStatus().equals(UserStatusEnum.DISABLE.getStatus())) {
            throw new BusinessException(MessageEnum.ACCOUNT_DISABLED.getCn());
        }
        UserInfo userInfoUpdate = new UserInfo();
        userInfoUpdate.setLastLoginTime(new Date());
        userInfoMapper.updateByUserId(userInfoUpdate, userInfo.getUserId());
        SessionWebUserDto sessionWebUserDto = new SessionWebUserDto();
        sessionWebUserDto.setNickName(userInfo.getNickName());
        sessionWebUserDto.setUserId(userInfo.getUserId());
        if (userInfo.getQqAvatar() != null) {
            sessionWebUserDto.setAvatar(userInfo.getQqAvatar());
        }
        if (ArrayUtils.contains(adminAccountConfig.getAdminEmail().split(","), userInfo.getEmail())) {
            sessionWebUserDto.setIsAdmin(true);
        } else {
            sessionWebUserDto.setIsAdmin(false);
        }
        return sessionWebUserDto;
    }

    /**
     * 忘记密码
     *
     * @param email     email
     * @param password  password
     * @param emailCode emailCode
     */
    @Override
    public void resetPassword(String email, String password, String emailCode) {
        emailCodeService.checkCode(email, emailCode);
        UserInfo userInfo = userInfoMapper.selectByEmail(email);
        if (userInfo == null) {
            throw new BusinessException(MessageEnum.ACCOUNT_DOES_NOT_EXISTS.getCn());
        }
        UserInfo userInfoUpdatePwd = new UserInfo();
        userInfoUpdatePwd.setUserId(userInfo.getUserId());
        userInfoUpdatePwd.setPassword(StringTools.encodeMD5(password));
        userInfoMapper.updateByUserId(userInfoUpdatePwd, userInfoUpdatePwd.getUserId());
    }

    /**
     * 获取用户使用空间情况
     * get from redis first;if result is null ,then select from mysql database
     *
     * @param userid
     * @return
     */
    @Override
    public UserSpaceDto getUseSpace(String userid) {
        String redisKey = StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_USER_SPACE, userid);
        UserSpaceDto userSpaceDto = (UserSpaceDto) redisUtils.get(redisKey);
        if (userSpaceDto == null) {
            userSpaceDto = new UserSpaceDto();
            UserInfo userInfo = userInfoMapper.selectByUserId(userid);
            userSpaceDto.setTotalSpace(userInfo.getTotalSpace());
            userSpaceDto.setUseSpace(userInfo.getUseSpace());
            //  6 hours expiration time
            redisUtils.set(redisKey, userSpaceDto, DateConstants.SECOND_HOUR * 6);
        }
        return userSpaceDto;
    }

    /**
     * 更新用户空间-MySQL
     *
     * @param userId
     * @param addUseSpace
     * @param addTotalSpace
     * @return
     */
    @Override
    public Integer updateUserSpace(String userId, Long addUseSpace, Long addTotalSpace) {
        return userInfoMapper.updateUserSpace(userId, addUseSpace, addTotalSpace);
    }

    @Override
    @Async
    public void createUserFolder(String userId) {
        String projectFolder = appConfig.getProjectFolder();
        //  用户目录
        String userFolderPath = projectFolder + OtherConstants.FILE_FOLDER_FILE + userId;
        File userFolder = new File(userFolderPath);
        if (!userFolder.exists()) {
            userFolder.mkdirs();
        }
    }
}