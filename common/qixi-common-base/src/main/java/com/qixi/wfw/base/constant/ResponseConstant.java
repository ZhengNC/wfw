package com.qixi.wfw.base.constant;

/**
 * @author ZhengNC
 * @date 2020/5/24 12:33
 */
public enum ResponseConstant {
    /**
     * 请求成功
     */
    SUCCESS("200", "请求成功"),

    /**
     * 参数错误
     */
    PARAM_FAILED("400", "参数错误"),

    /**
     * 权限不足
     */
    UNAUTHORIZED("401", "权限不足"),

    /**
     * 找不到资源
     */
    NOT_FOUND("404", "找不到资源"),

    /**
     * 资源已存在
     */
    EXISTED("460", "资源已存在"),

    /**
     * 请求失败
     */
    FAILED("500", "请求失败");

    public final String code;
    public final String msg;

    ResponseConstant(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
