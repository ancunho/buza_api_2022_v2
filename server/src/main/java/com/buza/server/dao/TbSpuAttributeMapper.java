package com.buza.server.dao;

import com.buza.server.entity.TbSpuAttribute;

public interface TbSpuAttributeMapper {
    int deleteByPrimaryKey(Integer spuAttrId);

    int insert(TbSpuAttribute record);

    int insertSelective(TbSpuAttribute record);

    TbSpuAttribute selectByPrimaryKey(Integer spuAttrId);

    int updateByPrimaryKeySelective(TbSpuAttribute record);

    int updateByPrimaryKey(TbSpuAttribute record);
}