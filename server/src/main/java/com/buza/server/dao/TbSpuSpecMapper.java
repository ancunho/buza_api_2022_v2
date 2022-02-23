package com.buza.server.dao;

import com.buza.server.entity.TbSpuSpec;

public interface TbSpuSpecMapper {
    int deleteByPrimaryKey(Integer spuSpecId);

    int insert(TbSpuSpec record);

    int insertSelective(TbSpuSpec record);

    TbSpuSpec selectByPrimaryKey(Integer spuSpecId);

    int updateByPrimaryKeySelective(TbSpuSpec record);

    int updateByPrimaryKey(TbSpuSpec record);
}