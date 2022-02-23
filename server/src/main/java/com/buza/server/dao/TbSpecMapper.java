package com.buza.server.dao;

import com.buza.server.entity.TbSpec;

public interface TbSpecMapper {
    int insert(TbSpec record);

    int insertSelective(TbSpec record);
}