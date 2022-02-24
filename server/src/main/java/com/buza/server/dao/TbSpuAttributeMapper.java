package com.buza.server.dao;

import com.buza.server.dto.TbSpuAttributeDto;
import com.buza.server.entity.TbSpuAttribute;

import java.util.List;

public interface TbSpuAttributeMapper {
    int deleteByPrimaryKey(Integer spuAttrId);

    int insert(TbSpuAttribute record);

    int insertSelective(TbSpuAttribute record);

    TbSpuAttribute selectByPrimaryKey(Integer spuAttrId);

    int updateByPrimaryKeySelective(TbSpuAttribute record);

    int updateByPrimaryKey(TbSpuAttribute record);

    List<TbSpuAttributeDto> lstTbSpuAttribute(TbSpuAttributeDto tbSpuAttributeDto);

    TbSpuAttributeDto getTbSpuAttributeInfoBySpuAttrId(Integer spuAttrId);

    List<TbSpuAttributeDto> lstTbSpuAttributeBySpuId(Integer spuId);

    List<TbSpuAttributeDto> lstTbSpuAttributeByAttrId(Integer attrId);
}