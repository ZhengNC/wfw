package com.qixi.wfw.user.controller;

import com.qixi.wfw.base.entity.dto.ResponseEntity;
import com.qixi.wfw.idgenerator.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhengNC
 * @date 2020/6/16 15:11
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestFeign testFeign;

    @GetMapping("hello")
    public ResponseEntity<String> hello(
            @RequestParam(value = "name", defaultValue = "default") String name){
        return testFeign.hello(name);
    }

    @GetMapping("admin/hello")
    public ResponseEntity<String> adminHello(){
        return ResponseEntity.success("admin");
    }
}
