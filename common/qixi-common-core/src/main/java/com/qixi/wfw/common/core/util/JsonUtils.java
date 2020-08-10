package com.qixi.wfw.common.core.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ZhengNC
 * @date 2020/6/19 17:41
 */
public class JsonUtils {

    /**
     * 克隆一个对象
     * @param object
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T cloneObj(Object object, Class<T> clazz){
        String temp = JSONObject.toJSONString(object);
        return JSONObject.parseObject(temp, clazz);
    }
}
