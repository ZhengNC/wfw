package com.qixi.wfw.user.service.impl;

import com.qixi.wfw.base.constant.RedisKeyPrefix;
import com.qixi.wfw.common.core.util.Tools;
import com.qixi.wfw.redis.RedisUtils;
import com.qixi.wfw.user.entity.po.UserAccount;
import com.qixi.wfw.user.mapper.UserAccountMapper;
import com.qixi.wfw.user.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhengNC
 * @date 2020/6/18 11:35
 */
@Service
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String login(String userPhone, String userPassword) {
        log.info("用户：{} 登录......", userPhone);
        UserAccount param = new UserAccount();
        param.setUserPhone(userPhone);
        UserAccount userAccount = userAccountMapper.selectOne(param);
        if (userAccount != null && userPassword.equals(userAccount.getUserPassword())){
            log.info("用户：{} 登录成功", userPhone);
            String token = Tools.randomStr(32, "lower");
            String key = RedisKeyPrefix.TOKEN_KEY + token;
            redisUtils.set(key, userAccount);
            return token;
        }
        log.info("用户：{} 登录失败", userPhone);
        return null;
    }
}
