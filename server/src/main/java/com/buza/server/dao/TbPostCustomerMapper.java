package com.buza.server.dao;

import com.buza.server.entity.TbPostCustomer;

public interface TbPostCustomerMapper {
    int deleteByPrimaryKey(Integer postCustomerId);

    int insert(TbPostCustomer record);

    int insertSelective(TbPostCustomer record);

    TbPostCustomer selectByPrimaryKey(Integer postCustomerId);

    int updateByPrimaryKeySelective(TbPostCustomer record);

    int updateByPrimaryKey(TbPostCustomer record);
}