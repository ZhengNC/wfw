package com.qixi.wfw.filter.global;

import com.alibaba.fastjson.JSONObject;
import com.qixi.wfw.base.constant.RedisKeyPrefix;
import com.qixi.wfw.base.constant.ResponseConstant;
import com.qixi.wfw.base.entity.dto.ResponseEntity;
import com.qixi.wfw.filter.config.PathRule;
import com.qixi.wfw.filter.config.PathRuleProperties;
import com.qixi.wfw.filter.util.PathMatcherUtil;
import com.qixi.wfw.redis.RedisUtils;
import com.qixi.wfw.user.dto.UserAccountDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author ZhengNC
 * @date 2020/6/16 16:52
 */
@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {


    @Autowired
    private PathRuleProperties pathRuleProperties;

    @Autowired
    private RedisUtils redisUtils;

    private boolean processPre(ServerWebExchange exchange){
        String path = exchange.getRequest().getPath().value();
        log.info("token filter pre:{}", path);
        List<PathRule> pathRules = pathRuleProperties.getPrePathRuleList("token");
        return PathMatcherUtil.matcher(path, pathRules);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (processPre(exchange)){
            boolean preResult = false;
            List<String> tokens = exchange.getRequest().getHeaders().get("token");
            String token = tokens != null && tokens.size() > 0 ? tokens.get(0) : null;
            if (StringUtils.isNotBlank(token)){
                //验证token
                String key = RedisKeyPrefix.TOKEN_KEY + token;
                UserAccountDto userAccountDto = redisUtils.get(key, UserAccountDto.class);
                log.info("token:{}", token);
                if (userAccountDto != null){
                    log.info("userName:{}", userAccountDto.getUserName());
                    preResult = true;
                }else {
                    log.info("token is invalid...");
                    preResult = false;
                }
            }else {
                log.info("token is empty...");
                preResult = false;
            }
            if (!preResult){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                ResponseEntity responseEntity =
                        ResponseEntity.failed(ResponseConstant.UNAUTHORIZED.code,
                                ResponseConstant.UNAUTHORIZED.msg);
                byte[] bytes = JSONObject.toJSONString(responseEntity).getBytes();
                DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap(bytes);
                exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return exchange.getResponse().writeWith(Flux.just(dataBuffer));
                //return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
