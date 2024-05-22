package com.easypan.aspect;


import com.easypan.annotation.GlobalInterceptor;
import com.easypan.annotation.VerifyParam;
import com.easypan.constants.OtherConstants;
import com.easypan.entity.dto.SessionWebUserDto;
import com.easypan.entity.enums.BasicDataTypesEnum;
import com.easypan.entity.enums.MessageEnum;
import com.easypan.entity.enums.ResponseCodeEnum;
import com.easypan.exception.BusinessException;
import com.easypan.utils.StringTools;
import com.easypan.utils.VerifyUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * @author c.w
 * @className OperationAspect
 * @description
 * @date 2024/05/10
 **/
@Aspect
@Component("operationAspect")
@Slf4j
public class OperationAspect {
    private static final List<String> baseDataTypes = BasicDataTypesEnum.getAllDataTypes();

    @Pointcut("@annotation(com.easypan.annotation.GlobalInterceptor)")
    private void requestInterceptor() {
    }

    /**
     * 切点前
     *
     * @param joinPoint 切点
     */
    @Before("requestInterceptor()")
    private void interceptorDo(JoinPoint joinPoint) {
        try {
            //  获取具体目标
            Object target = joinPoint.getTarget();
            //  取参数值
            Object[] args = joinPoint.getArgs();
            //  获取方法名
            String methodName = joinPoint.getSignature().getName();
            //  获取参数类型
            Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
            //  获取方法
            Method method = target.getClass().getMethod(methodName, parameterTypes);
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
            if (interceptor == null) {
                return;
            }
            if (interceptor.checkLogin()) {
                checkLogin();
            }
            //  校验参数
            if (interceptor.checkParams()) {
                validateParams(method, args);
            }
        } catch (BusinessException e) {
            log.error("异常数据拦截:{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("拦截器异常:{}", e.getMessage());
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }

    /**
     * 校验参数列表
     *
     * @param method 方法
     * @param params 参数
     */
    private void validateParams(Method method, Object[] params) {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = params[i];
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            if (verifyParam == null) {
                continue;
            }
            if (baseDataTypes.contains(parameter.getParameterizedType().getTypeName())) {
                checkValue(arg, verifyParam);
            } else {
                //checkValue();
                // TODO 校验对象
            }
        }
    }

    /**
     * 校验   前提:verifyParam not null
     *
     * @param value     校验内容 (对象)
     * @param parameter 参数 not null
     */
    private void checkValue(Object value, Parameter parameter) {
        // TODO 校验对象
    }

    /**
     * 校验   前提:verifyParam not null
     *
     * @param value       校验内容 (基础数据类型)
     * @param verifyParam 注解 not null
     */
    private void checkValue(Object value, VerifyParam verifyParam) {
        boolean isEmpty = value == null || StringTools.isEmpty(value.toString());
        int length = value == null ? 0 : value.toString().length();
        //  校验空
        if (isEmpty && verifyParam.required()) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        if (!isEmpty) {
            //  长度校验
            if (verifyParam.max() != -1 && verifyParam.max() < length || verifyParam.min() != -1 && verifyParam.min() > length) {
                throw new BusinessException(ResponseCodeEnum.CODE_600);
            }
            //  校验正则
            if (!StringTools.isEmpty(verifyParam.regex().getRegex()) && !VerifyUtils.verify(value.toString(), verifyParam.regex())) {
                throw new BusinessException(ResponseCodeEnum.CODE_600);
            }
        }
    }

    public void checkLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        SessionWebUserDto sessionWebUserDto = (SessionWebUserDto) session.getAttribute(OtherConstants.SESSION_KEY);
        //  未登录
        if (sessionWebUserDto == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }

    }
}


