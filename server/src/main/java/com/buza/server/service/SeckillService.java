package com.buza.server.service;

import com.buza.server.entity.TbSeckill;

public interface SeckillService {

    TbSeckill selectByPrimaryKey(Long seckillId);

}
