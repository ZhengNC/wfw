package com.qixi.wfw.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author ZhengNC
 * @date 2020/6/23 11:35
 */
@Configuration
public class AccessTokenConfig {

    @Bean
    TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
}
