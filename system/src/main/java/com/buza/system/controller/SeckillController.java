package com.buza.system.controller;

import com.buza.server.common.BaseResponse;
import com.buza.server.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/seckill")
public class SeckillController {

    @Resource
    private SeckillService seckillService;

    @RequestMapping("/info")
    public BaseResponse selectByPrimaryKey() {
        return BaseResponse.valueOfSuccess(seckillService.selectByPrimaryKey(1000L));
    }

}
