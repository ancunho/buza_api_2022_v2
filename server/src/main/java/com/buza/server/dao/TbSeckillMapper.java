package com.buza.server.dao;

import com.buza.server.entity.TbSeckill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
public interface TbSeckillMapper {
    int deleteByPrimaryKey(Long seckillId);

    int insert(TbSeckill record);

    int insertSelective(TbSeckill record);

    TbSeckill selectByPrimaryKey(Long seckillId);

    int updateByPrimaryKeySelective(TbSeckill record);

    int updateByPrimaryKey(TbSeckill record);

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 如果影响行数 = 1， 表示更新的记录行数
     * @return 如果影响行数 = 1， 表示更新的记录行数
     */
    int reduceQty(long seckillId, Date killTime);

    List<TbSeckill> getAllTbSeckill();

}