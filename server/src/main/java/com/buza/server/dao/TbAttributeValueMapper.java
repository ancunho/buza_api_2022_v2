package com.buza.server.dao;

import com.buza.server.entity.TbAttributeValue;

public interface TbAttributeValueMapper {
    int deleteByPrimaryKey(Integer attrValueId);

    int insert(TbAttributeValue record);

    int insertSelective(TbAttributeValue record);

    TbAttributeValue selectByPrimaryKey(Integer attrValueId);

    int updateByPrimaryKeySelective(TbAttributeValue record);

    int updateByPrimaryKey(TbAttributeValue record);
}