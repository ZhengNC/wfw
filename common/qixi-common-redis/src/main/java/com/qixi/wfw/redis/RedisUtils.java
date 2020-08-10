package com.qixi.wfw.redis;

import com.alibaba.fastjson.JSONObject;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhengNC
 * @date 2020/6/19 15:15
 */
@Component
public class RedisUtils {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 存储数据
     *
     * @param key 键
     * @param value 值
     */
    public void set(@NotBlank String key, Object value){
        if (key == null || "".equals(key.trim())){
            throw new IllegalArgumentException("key can not blank");
        }
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(toJson(value));
    }

    /**
     * 存储数据（有过期时间，单位：秒）
     *
     * @param key 键
     * @param value 值
     * @param expire 过期时间，单位：秒
     */
    public void set(@NotBlank String key, Object value, long expire){
        if (key == null || "".equals(key.trim())){
            throw new IllegalArgumentException("key can not blank");
        }
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(toJson(value), expire, TimeUnit.SECONDS);
    }

    /**
     * 存储数据（有过期时间）
     *
     * @param key 键
     * @param value 值
     * @param expire 过期时间
     * @param timeUnit 过期时间单位
     */
    public void set(@NotBlank String key, Object value, long expire, TimeUnit timeUnit){
        if (key == null || "".equals(key.trim())){
            throw new IllegalArgumentException("key can not blank");
        }
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(toJson(value), expire, timeUnit);
    }

    /**
     * 设置过期时间（单位：秒）
     *
     * @param key 键
     * @param expire 过期时间（单位：秒）
     */
    public void expire(@NotBlank String key, long expire){
        if (key == null || "".equals(key.trim())){
            throw new IllegalArgumentException("key can not blank");
        }
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.expire(expire, TimeUnit.SECONDS);
    }

    /**
     * 设置过期时间
     *
     * @param key 键
     * @param expire 过期时间
     * @param timeUnit 过期时间单位
     */
    public void expire(@NotBlank String key, long expire, TimeUnit timeUnit){
        if (key == null || "".equals(key.trim())){
            throw new IllegalArgumentException("key can not blank");
        }
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.expire(expire, timeUnit);
    }

    /**
     * 获取字符串结果
     *
     * @param key 键
     * @return
     */
    public String get(String key){
        if (key == null || "".equals(key.trim())){
            return null;
        }
        RBucket<String> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    /**
     * 获取指定类型结果
     *
     * @param key 键
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz){
        if (key == null || "".equals(key.trim())){
            return null;
        }
        RBucket<String> bucket = redissonClient.getBucket(key);
        return JSONObject.parseObject(bucket.get(), clazz);
    }

    /**
     * 删除数据
     *
     * @param key 键
     * @return
     */
    public boolean delete(String key){
        if (key == null || "".equals(key.trim())){
            throw new IllegalArgumentException("key can not blank");
        }
        RBucket<String> bucket = redissonClient.getBucket(key);
        return bucket.delete();
    }

    private String toJson(Object obj){
        if (obj == null){
            return null;
        }
        if (obj instanceof Integer || obj instanceof Long || obj instanceof Float || obj instanceof Double
                || obj instanceof Boolean || obj instanceof String){
            return String.valueOf(obj);
        }
        return JSONObject.toJSONString(obj);
    }
}
