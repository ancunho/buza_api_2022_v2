package com.buza.server.dao;

import com.buza.server.entity.TbCustomerShop;

public interface TbCustomerShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbCustomerShop record);

    int insertSelective(TbCustomerShop record);

    TbCustomerShop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbCustomerShop record);

    int updateByPrimaryKey(TbCustomerShop record);
}