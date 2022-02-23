package com.buza.server.dao;

import com.buza.server.entity.TbSkuSpec;

public interface TbSkuSpecMapper {
    int deleteByPrimaryKey(Integer skuSpecId);

    int insert(TbSkuSpec record);

    int insertSelective(TbSkuSpec record);

    TbSkuSpec selectByPrimaryKey(Integer skuSpecId);

    int updateByPrimaryKeySelective(TbSkuSpec record);

    int updateByPrimaryKey(TbSkuSpec record);
}