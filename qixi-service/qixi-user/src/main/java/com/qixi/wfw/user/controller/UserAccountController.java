package com.qixi.wfw.user.controller;

import com.qixi.wfw.base.entity.dto.ResponseEntity;
import com.qixi.wfw.user.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author ZhengNC
 * @date 2020/6/18 9:00
 */
@RestController
@RequestMapping("account")
@Validated
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    /**
     * 登录方法
     * @param userPhone
     * @param userPassword
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<String> register(
            @RequestParam("userPhone")
            @NotBlank(message = "手机号不能为空")
            @Pattern(regexp = "^1[3456789]\\d{9}", message = "手机号格式错误")
                    String userPhone,
            @RequestParam("userPassword")
            @NotBlank(message = "密码不能为空")
                    String userPassword){
        return ResponseEntity.success(userAccountService.login(userPhone, userPassword));
    }
}
