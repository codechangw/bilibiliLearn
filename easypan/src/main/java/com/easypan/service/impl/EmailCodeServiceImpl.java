package com.easypan.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import com.easypan.component.redis.RedisComponent;
import com.easypan.component.redis.RedisUtils;
import com.easypan.constants.DateConstants;
import com.easypan.constants.MessageConstants;
import com.easypan.constants.RedisKeyConstants;
import com.easypan.entity.config.EmailConfig;
import com.easypan.entity.dto.SysSettingDto;
import com.easypan.entity.enums.MessageEnum;
import com.easypan.entity.po.UserInfo;
import com.easypan.entity.query.UserInfoQuery;
import com.easypan.exception.BusinessException;
import com.easypan.mappers.UserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.easypan.entity.enums.PageSize;
import com.easypan.entity.query.EmailCodeQuery;
import com.easypan.entity.po.EmailCode;
import com.easypan.entity.vo.PaginationResultVO;
import com.easypan.entity.query.SimplePage;
import com.easypan.mappers.EmailCodeMapper;
import com.easypan.service.EmailCodeService;
import com.easypan.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * 邮箱验证码 业务接口实现
 *
 * @author C.w
 */
@Service("emailCodeService")
public class EmailCodeServiceImpl implements EmailCodeService {

    private static final Logger logger = LoggerFactory.getLogger(EmailCodeServiceImpl.class);
    @Resource
    private EmailCodeMapper<EmailCode, EmailCodeQuery> emailCodeMapper;

    @Resource
    private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private EmailConfig emailConfig;
    @Resource
    private RedisComponent redis;
    @Resource
    private RedisUtils<String> redisUtils;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<EmailCode> findListByParam(EmailCodeQuery param) {
        return this.emailCodeMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(EmailCodeQuery param) {
        return this.emailCodeMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<EmailCode> findListByPage(EmailCodeQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<EmailCode> list = this.findListByParam(param);
        PaginationResultVO<EmailCode> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(EmailCode bean) {
        return this.emailCodeMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<EmailCode> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.emailCodeMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<EmailCode> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.emailCodeMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(EmailCode bean, EmailCodeQuery param) {
        StringTools.checkParam(param);
        return this.emailCodeMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(EmailCodeQuery param) {
        StringTools.checkParam(param);
        return this.emailCodeMapper.deleteByParam(param);
    }

    /**
     * 根据EmailAndCode获取对象
     */
    @Override
    public EmailCode getEmailCodeByEmailAndCode(String email, String code) {
        return this.emailCodeMapper.selectByEmailAndCode(email, code);
    }

    /**
     * 根据EmailAndCode修改
     */
    @Override
    public Integer updateEmailCodeByEmailAndCode(EmailCode bean, String email, String code) {
        return this.emailCodeMapper.updateByEmailAndCode(bean, email, code);
    }

    /**
     * 根据EmailAndCode删除
     */
    @Override
    public Integer deleteEmailCodeByEmailAndCode(String email, String code) {
        return this.emailCodeMapper.deleteByEmailAndCode(email, code);
    }

    @Override
    // 支持事务注解
    @Transactional(rollbackFor = Exception.class)
    public void sendEmailCode(String email, Integer type) {
        // 如果是注册
        if (type.equals(DateConstants.ZERO)) {
            UserInfo userInfo = userInfoMapper.selectByEmail(email);
            if (userInfo != null) {
                throw new BusinessException(MessageEnum.EMAIL_EXITS.getCn());
            }
        }
        String code = StringTools.getRandomNumber(DateConstants.LENGTH_5);
        String key = StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_EMAIL_CODE, email);
        // 发送验证码
        sendEmailCode(email, code);
        //  新验证码存入redis     有效时间5分钟
        redisUtils.set(key, code, emailConfig.getEmailCodeValidTime() * 60);
    }

    /**
     * 检查验证码
     *
     * @param email email
     * @param code  code
     */
    @Override
    public void checkCode(String email, String code) {
        String codeEmailKey = StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_EMAIL_CODE, email);
        String codeEmail = redisUtils.get(codeEmailKey);
        //  邮箱 验证码失效或没有验证码
        if (codeEmail == null || codeEmail.isEmpty()) {
            throw new BusinessException(MessageEnum.CODE_EMAIL_INVALIDATED.getCn());
        }
        if (!code.equals(codeEmail)) {
            throw new BusinessException(MessageEnum.CODE_EMAIL_ERROR.getCn());
        }
        //  注册成功,删除key
        redisUtils.delete(codeEmailKey);
    }

    /**
     * 发送邮箱验证码
     *
     * @param targetEmail target email
     * @param code        code
     */
    private void sendEmailCode(String targetEmail, String code) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(emailConfig.getSendUserName());
            helper.setTo(targetEmail);

            SysSettingDto sysSettingDto = redis.getSysSettingDto();
            helper.setSubject(sysSettingDto.getRegisterMailTitle());
            helper.setText(String.format(sysSettingDto.getRegisterMailContent(), code));
            helper.setSentDate(new Date());
            javaMailSender.send(message);
        } catch (Exception e) {
            logger.info(MessageConstants.EMAIL_SEND_ERROR, e);
            throw new BusinessException(MessageConstants.EMAIL_SEND_ERROR);
        }
    }
}