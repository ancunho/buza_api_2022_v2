package com.buza.server.dao;

import com.buza.server.entity.TbCategory;

public interface TbCategoryMapper {
    int insert(TbCategory record);

    int insertSelective(TbCategory record);
}