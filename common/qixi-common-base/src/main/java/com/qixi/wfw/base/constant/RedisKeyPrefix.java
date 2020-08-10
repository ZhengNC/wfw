package com.qixi.wfw.base.constant;

/**
 * @author ZhengNC
 * @date 2020/6/19 15:10
 */
public interface RedisKeyPrefix {
    String BASE_KEY = "qixi:wfw:";

    String USER_KEY = BASE_KEY + "user:";

    String TOKEN_KEY = USER_KEY + "token:";
}
