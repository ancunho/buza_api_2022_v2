package com.buza.server.dao;

import com.buza.server.dto.TbAttributeDto;
import com.buza.server.dto.TbAttributeValueDto;
import com.buza.server.entity.TbAttributeValue;

import java.util.List;

public interface TbAttributeValueMapper {
    int deleteByPrimaryKey(Integer attrValueId);

    int insert(TbAttributeValue record);

    int insertSelective(TbAttributeValue record);

    TbAttributeValue selectByPrimaryKey(Integer attrValueId);

    int updateByPrimaryKeySelective(TbAttributeValue record);

    int updateByPrimaryKey(TbAttributeValue record);

    List<TbAttributeValueDto> lstTbAttributeValue(TbAttributeDto tbAttributeDto);

    TbAttributeValueDto getTbAttributeValueInfoByAttrValueId(Integer attrValueId);
}