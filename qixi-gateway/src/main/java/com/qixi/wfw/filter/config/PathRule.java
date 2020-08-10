package com.qixi.wfw.filter.config;

import javax.validation.constraints.NotNull;

/**
 * @author ZhengNC
 * @date 2020/6/17 17:58
 */
public class PathRule {
    public final String path;
    public final Boolean rule;

    public PathRule(@NotNull String path, @NotNull Boolean rule){
        this.path = path;
        this.rule = rule;
    }
}
