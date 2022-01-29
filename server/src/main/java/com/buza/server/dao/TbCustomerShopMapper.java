package com.buza.server.dao;

import com.buza.server.dto.TbCustomerShopDto;
import com.buza.server.entity.TbCustomerShop;

import java.util.List;

public interface TbCustomerShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbCustomerShop record);

    int insertSelective(TbCustomerShop record);

    TbCustomerShop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbCustomerShop record);

    int updateByPrimaryKey(TbCustomerShop record);

    void deleteByCustomerId(Integer customerId);

    List<TbCustomerShopDto> getCustomerShopListByCustomerId(Integer customerId);
}