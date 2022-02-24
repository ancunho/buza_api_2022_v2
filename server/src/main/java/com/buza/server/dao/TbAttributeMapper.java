package com.buza.server.dao;

import com.buza.server.dto.TbAttributeDto;
import com.buza.server.entity.TbAttribute;

import java.util.List;

public interface TbAttributeMapper {
    int deleteByPrimaryKey(Integer attrId);

    int insert(TbAttribute record);

    int insertSelective(TbAttribute record);

    TbAttribute selectByPrimaryKey(Integer attrId);

    int updateByPrimaryKeySelective(TbAttribute record);

    int updateByPrimaryKey(TbAttribute record);

    List<TbAttributeDto> lstTbAttribute(TbAttributeDto tbAttributeDto);

    TbAttributeDto getTbAttributeInfoByAttrId(Integer attrId);
}