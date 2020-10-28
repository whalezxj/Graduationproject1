package com.example.demo.utils;

import com.example.demo.enums.ResultEnum;
import com.example.demo.exceptions.MyException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author
 * @date 2020/8/26
 **/
public class UserUtil {

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public static Object getUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.getPrincipal() == null) {
            throw new MyException(ResultEnum.NOT_LOGIN_ERROR);
        }
        return currentUser.getPrincipal();
    }
}
