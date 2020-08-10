package com.qixi.wfw.idgenerator.controller;

import com.qixi.wfw.idgenerator.service.QixiIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ID生成接口
 *
 * @author ZhengNC
 * @date 2020/6/18 17:56
 */
@RestController
@RequestMapping("generator")
public class IdGeneratorController {

    @Autowired
    private QixiIdWorker qixiIdWorker;

    /**
     * 获取下一个ID
     *
     * @return
     */
    @GetMapping("nextId")
    public long nextId(){
        return qixiIdWorker.nextId();
    }
}
