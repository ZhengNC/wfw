package com.qixi.wfw.filter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhengNC
 * @date 2020/6/17 15:49
 */
@Configuration
@ConfigurationProperties(prefix = "qixi.gateway")
public class PathRuleProperties {

    private List<Filter> filters;

    private Map<String, List<PathRule>> preMatcherMap = new HashMap<String, List<PathRule>>();
    private Map<String, List<PathRule>> postMatcherMap = new HashMap<String, List<PathRule>>();

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
        preMatcherMap.clear();
        postMatcherMap.clear();
        if (filters != null && filters.size() > 0){
            for (Filter filter : filters){
                List<PathRule> prePathRules = new ArrayList<PathRule>();
                preMatcherMap.put(filter.id, prePathRules);
                if (filter.pre != null && filter.pre.size() > 0){
                    for (String preStr : filter.pre){
                        if (preStr != null){
                            String[] preStrArr = preStr.split(" ");
                            if (preStrArr != null && preStrArr.length == 2){
                                String path = preStrArr[0];
                                Boolean rule = "true".equals(preStrArr[1]) ? true : false;
                                prePathRules.add(new PathRule(path, rule));
                            }
                        }
                    }
                }

                List<PathRule> postPathRules = new ArrayList<PathRule>();
                postMatcherMap.put(filter.id, postPathRules);
                if (filter.post != null && filter.post.size() > 0){
                    for (String postStr : filter.post){
                        if (postStr != null){
                            String[] postStrArr = postStr.split(" ");
                            if (postStrArr != null && postStrArr.length == 2){
                                String path = postStrArr[0];
                                Boolean rule = "true".equals(postStrArr[1]) ? true : false;
                                postPathRules.add(new PathRule(path, rule));
                            }
                        }
                    }
                }
            }
        }
    }

    public List<PathRule> getPrePathRuleList(String id){
        return preMatcherMap.get(id);
    }

    public List<PathRule> getPostPathRuleList(String id){
        return postMatcherMap.get(id);
    }
}

class Filter{
    String id;
    List<String> pre;
    List<String> post;

    public void setId(String id) {
        this.id = id;
    }

    public void setPre(List<String> pre) {
        this.pre = pre;
    }

    public void setPost(List<String> post) {
        this.post = post;
    }
}
