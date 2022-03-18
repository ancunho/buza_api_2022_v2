package com.buza.server.service.impl;

import com.buza.server.dao.TbSeckillMapper;
import com.buza.server.entity.TbSeckill;
import com.buza.server.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;

public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private TbSeckillMapper tbSeckillMapper;

    public TbSeckill selectByPrimaryKey(Long seckillId) {
        return tbSeckillMapper.selectByPrimaryKey(seckillId);
    }
}
