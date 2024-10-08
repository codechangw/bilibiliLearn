package com.easypan.utils;

import com.easypan.constants.DateConstants;
import com.easypan.exception.BusinessException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


public class StringTools {

    private static final Character colon = ':';

    public static void checkParam(Object param) {
        try {
            Field[] fields = param.getClass().getDeclaredFields();
            boolean notEmpty = false;
            for (Field field : fields) {
                String methodName = "get" + StringTools.upperCaseFirstLetter(field.getName());
                Method method = param.getClass().getMethod(methodName);
                Object object = method.invoke(param);
                if (object != null && object instanceof java.lang.String && !StringTools.isEmpty(object.toString())
                        || object != null && !(object instanceof java.lang.String)) {
                    notEmpty = true;
                    break;
                }
            }
            if (!notEmpty) {
                throw new BusinessException("多参数更新，删除，必须有非空条件");
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("校验参数是否为空失败");
        }
    }

    public static String upperCaseFirstLetter(String field) {
        if (isEmpty(field)) {
            return field;
        }
        //如果第二个字母是大写，第一个字母不大写
        if (field.length() > 1 && Character.isUpperCase(field.charAt(1))) {
            return field;
        }
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    public static boolean isEmpty(String str) {
        if (null == str || str.isEmpty() || "null".equals(str) || "\u0000".equals(str)) {
            return true;
        } else return str.trim().isEmpty();
    }

    public static String getRandomNumber(Integer length) {
        return RandomStringUtils.random(length, false, true);
    }

    public static String encodeMD5(String originStr) {
        return isEmpty(originStr) ? null : DigestUtils.md5Hex(originStr);
    }

    /**
     * redisKey 拼接
     *
     * @param head key head
     * @param strs key body
     * @return String
     */
    public static String redisKeyJointH(String head, String... strs) {
        StringBuilder stringBuilder = new StringBuilder();
        if (head != null && !head.isEmpty()) {
            stringBuilder.append(head);
            if (!(head.charAt(head.length() - 1) == colon)) {
                stringBuilder.append(colon);
            }
        }
        if (strs != null) {
            for (String str : strs) {
                if (isNotNull(str)) {
                    stringBuilder.append(str);
                    stringBuilder.append(colon);
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * redis key 拼接
     *
     * @param strs s
     * @return key
     */
    public static String redisKeyJoint(String... strs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strs) {
            if (isNotNull(str)) {
                stringBuilder.append(str);
                stringBuilder.append(colon);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 判断字符串是否为空且长度>=0
     *
     * @return true 不为空,false null或者length=0
     */
    public static Boolean isNotNull(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean pathIsOk(String path) {
        if (StringTools.isEmpty(path)) {
            return true;
        }
        if (path.contains("../") || path.contains("..\\")) {
            return false;
        }
        return true;
    }

    public static String rename(String file) {
        String fileName = getFileName(file);
        String suffix = getFileSuffix(file);
        String randomNumber = getRandomNumber(DateConstants.LENGTH_5);
        return fileName + "_" + randomNumber + suffix;
    }

    public static String getFileName(String file) {
        int lastIndex = file.lastIndexOf(".");
        if (lastIndex < 0) {
            return file;
        }
        return file.substring(0, lastIndex);
    }

    public static String getFileSuffix(String file) {
        int lastIndex = file.lastIndexOf(".");
        if (lastIndex < 0) {
            return "";
        }
        return file.substring(lastIndex);
    }

}
