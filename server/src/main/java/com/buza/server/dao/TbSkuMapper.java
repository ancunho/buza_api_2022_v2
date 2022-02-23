package com.buza.server.dao;

import com.buza.server.entity.TbSku;

public interface TbSkuMapper {
    int deleteByPrimaryKey(Integer skuId);

    int insert(TbSku record);

    int insertSelective(TbSku record);

    TbSku selectByPrimaryKey(Integer skuId);

    int updateByPrimaryKeySelective(TbSku record);

    int updateByPrimaryKey(TbSku record);
}