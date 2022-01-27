package com.buza.server.dao;

import com.buza.server.dto.TbCustomerDto;
import com.buza.server.entity.TbCustomer;

import java.util.List;
import java.util.Map;

public interface TbCustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(TbCustomer record);

    int insertSelective(TbCustomer record);

    TbCustomer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(TbCustomer record);

    int updateByPrimaryKey(TbCustomer record);

    Integer isExistMobileNo(Map<String, Object> mapParams);

    List<TbCustomerDto> getAllCustomerList();

    TbCustomerDto getCustomerInfoByCustomerId(Integer customerId);
}