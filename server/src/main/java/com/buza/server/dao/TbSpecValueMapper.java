package com.buza.server.dao;

import com.buza.server.entity.TbSpecValue;

public interface TbSpecValueMapper {
    int deleteByPrimaryKey(Integer specValueId);

    int insert(TbSpecValue record);

    int insertSelective(TbSpecValue record);

    TbSpecValue selectByPrimaryKey(Integer specValueId);

    int updateByPrimaryKeySelective(TbSpecValue record);

    int updateByPrimaryKey(TbSpecValue record);
}