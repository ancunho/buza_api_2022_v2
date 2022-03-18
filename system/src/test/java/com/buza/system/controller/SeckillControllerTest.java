package com.buza.system.controller;


import com.buza.server.entity.TbSeckill;
import com.buza.server.service.SeckillService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@MybatisTest
class SeckillControllerTest {

    @Resource
    private SeckillService seckillService;

    @Test
    void selectByPrimaryKey() {
        TbSeckill tbSeckill = seckillService.selectByPrimaryKey(1000L);

    }
}