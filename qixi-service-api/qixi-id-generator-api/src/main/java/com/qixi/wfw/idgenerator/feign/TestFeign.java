package com.qixi.wfw.idgenerator.feign;

import com.qixi.wfw.base.constant.ServiceNameConstant;
import com.qixi.wfw.base.entity.dto.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhengNC
 * @date 2020/6/16 14:57
 */
@FeignClient(value = ServiceNameConstant.QIXI_ID_GENERATOR, path = "id/test")
public interface TestFeign {

    /**
     * hello
     * @param name
     * @return
     */
    @GetMapping("hello")
    ResponseEntity<String> hello(
            @RequestParam(value = "name", defaultValue = "default") String name);
}
