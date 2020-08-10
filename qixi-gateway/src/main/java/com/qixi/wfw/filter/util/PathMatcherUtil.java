package com.qixi.wfw.filter.util;

import com.qixi.wfw.filter.config.PathRule;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * @author ZhengNC
 * @date 2020/6/17 17:56
 */
public class PathMatcherUtil {

    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    public static boolean matcher(String path, List<PathRule> pathRules){
        boolean result = true;
        int conformity = 0;
        int maxConformity = path.length();
        for (PathRule pathRule : pathRules){
            if (MATCHER.match(path, pathRule.path)){
                if (pathRule.path.length() > conformity){
                    if (pathRule.path.endsWith("/**")){
                        conformity = pathRule.path.length()-3;
                    }else{
                        conformity = pathRule.path.length();
                    }
                    result = pathRule.rule;
                    if (conformity == maxConformity){
                        break;
                    }
                }
            }
        }
        return result;
    }
}
