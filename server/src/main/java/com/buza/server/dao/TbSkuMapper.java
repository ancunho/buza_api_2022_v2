package com.buza.server.dao;

import com.buza.server.dto.TbSkuDto;
import com.buza.server.entity.TbSku;

import java.util.List;

public interface TbSkuMapper {
    int deleteByPrimaryKey(Integer skuId);

    int insert(TbSku record);

    int insertSelective(TbSku record);

    TbSku selectByPrimaryKey(Integer skuId);

    int updateByPrimaryKeySelective(TbSku record);

    int updateByPrimaryKey(TbSku record);

    List<TbSkuDto> lstTbSku(TbSkuDto tbSkuDto);

    TbSkuDto getTbSkuInfoBySkuId(Integer skuId);

    List<TbSkuDto> lstTbSkuBySpuId(Integer spuId);
}