package com.easypan.controller;


import com.easypan.annotation.GlobalInterceptor;
import com.easypan.annotation.VerifyParam;
import com.easypan.component.redis.RedisUtils;
import com.easypan.constants.DateConstants;
import com.easypan.constants.RedisKeyConstants;
import com.easypan.entity.dto.CreateImageCode;
import com.easypan.entity.enums.MessageEnum;
import com.easypan.entity.enums.VerifyRegexEnum;
import com.easypan.entity.vo.ResponseVO;
import com.easypan.exception.BusinessException;
import com.easypan.service.EmailCodeService;
import com.easypan.service.UserInfoService;
import com.easypan.utils.StringTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户信息表 Controller
 *
 * @author C.W
 */
@RestController("userInfoController")
public class AccountController extends ABaseController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private EmailCodeService emailCodeService;
    @Resource
    private RedisUtils<String> redisUtils;


    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type) throws
            IOException {
        CreateImageCode v = new CreateImageCode(130, 38, 5, 10);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = v.getCode();
        //  图片验证码有效时间1分钟
        //  图片验证码
        if (type == null || type == 0) {
            redisUtils.set(StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_CODE_IMAGE, session.getId()), code, DateConstants.LENGTH_1 * 60);
        } else {
            //  注册时发送邮箱验证码前的图片验证码
            redisUtils.set(StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_CODE_IMAGE_EMAIL, session.getId()), code, DateConstants.LENGTH_1 * 60);
        }
        v.write(response.getOutputStream());
    }

    @RequestMapping("sendEmailCode")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO sendEmailCode(HttpSession session,
                                    @VerifyParam(regex = VerifyRegexEnum.EMAIL) String email,
                                    @VerifyParam(regex = VerifyRegexEnum.CHECK_CODE) String checkCode,
                                    Integer type) {
        String key = StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_CODE_IMAGE_EMAIL, session.getId());
        String redisCode = redisUtils.get(key);
        if (redisCode.isEmpty()) {
            throw new BusinessException(MessageEnum.CODE_INVALIDATED.getCn());
        }
        if (!checkCode.equalsIgnoreCase(redisCode)) {
            throw new BusinessException(MessageEnum.CODE_IMG_ERROR.getCn());
        }
        emailCodeService.sendEmailCode(email, type);
        return getSuccessResponseVO(null);
    }

    /**
     * 注册
     *
     * @param session   session
     * @param email     type
     * @param emailCode emailCode
     * @param nickName  nickName
     * @param password  password
     * @param checkCode checkCode
     * @return ResponseVO
     */
    @RequestMapping("register")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO register(HttpSession session,
                               @VerifyParam(regex = VerifyRegexEnum.EMAIL) String email,
                               @VerifyParam(regex = VerifyRegexEnum.NUMBER_5) String emailCode,
                               @VerifyParam(regex = VerifyRegexEnum.CHARACTER_NUMBER) String nickName,
                               @VerifyParam(regex = VerifyRegexEnum.PASSWORD) String password,
                               @VerifyParam(regex = VerifyRegexEnum.CHECK_CODE) String checkCode) {
        String codeImageKey = StringTools.redisKeyJointH(RedisKeyConstants.REDIS_KEY_CODE_IMAGE, session.getId());
        String codeImage = redisUtils.get(codeImageKey);
        //  if not equal
        if (!checkCode.equalsIgnoreCase(codeImage)) {
            throw new BusinessException(MessageEnum.CODE_IMG_ERROR.getCn());
        }
        userInfoService.register(email, nickName, password, emailCode);
        return getSuccessResponseVO(null);
    }

}