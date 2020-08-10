package com.qixi.wfw.user.service;

/**
 * @author ZhengNC
 * @date 2020/6/18 11:33
 */
public interface UserAccountService {

    /**
     * 登录操作
     * @param userPhone
     * @param userPassword
     * @return
     */
    String login(String userPhone, String userPassword);
}
