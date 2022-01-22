package com.buza.server.dao;

import com.buza.server.entity.TbCustomer;

public interface TbCustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(TbCustomer record);

    int insertSelective(TbCustomer record);

    TbCustomer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(TbCustomer record);

    int updateByPrimaryKey(TbCustomer record);
}