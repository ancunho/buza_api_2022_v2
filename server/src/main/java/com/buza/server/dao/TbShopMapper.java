package com.buza.server.dao;

import com.buza.server.entity.TbShop;

import java.util.Map;

public interface TbShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(TbShop record);

    int insertSelective(TbShop record);

    TbShop selectByPrimaryKey(Integer shopId);

    int updateByPrimaryKeySelective(TbShop record);

    int updateByPrimaryKey(TbShop record);

    Integer isExistShopName(Map<String, Object> mapParams);
}