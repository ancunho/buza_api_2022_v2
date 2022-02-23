package com.buza.server.dao;

import com.buza.server.entity.TbBrand;

public interface TbBrandMapper {
    int deleteByPrimaryKey(Integer brandId);

    int insert(TbBrand record);

    int insertSelective(TbBrand record);

    TbBrand selectByPrimaryKey(Integer brandId);

    int updateByPrimaryKeySelective(TbBrand record);

    int updateByPrimaryKey(TbBrand record);
}