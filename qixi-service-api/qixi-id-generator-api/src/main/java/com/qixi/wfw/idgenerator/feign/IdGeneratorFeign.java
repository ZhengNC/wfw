package com.qixi.wfw.idgenerator.feign;

import com.qixi.wfw.base.constant.ServiceNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ID生成接口
 *
 * @author ZhengNC
 * @date 2020/6/18 18:06
 */
@FeignClient(value = ServiceNameConstant.QIXI_ID_GENERATOR, path = "id/generator")
public interface IdGeneratorFeign {

    /**
     * 获取下一个ID
     * @return
     */
    @GetMapping("nextId")
    long nextId();
}
