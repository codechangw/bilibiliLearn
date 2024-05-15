package com.easypan.utils;

import com.easypan.entity.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className VerifyUtils
 * @description  正则校验
 * @author c.w
 * @date 2024/05/10
**/
public class VerifyUtils {
    public static Boolean verify(String value, VerifyRegexEnum verifyRegexEnum){
        if(StringTools.isEmpty(value)){
            return false;
        }
        Pattern pattern = Pattern.compile(verifyRegexEnum.getRegex());
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    public static Boolean verify(String value, String regex){
        if(StringTools.isEmpty(value)){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}


