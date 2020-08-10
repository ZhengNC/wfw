package com.qixi.wfw.idgenerator.controller;

import com.qixi.wfw.base.entity.dto.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhengNC
 * @date 2020/6/16 11:40
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("hello")
    public ResponseEntity<String> hello(
            @RequestParam(value = "name", defaultValue = "default") String name){
        return ResponseEntity.success(name);
    }
}
