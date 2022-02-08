package com.buza.server.dao;

import com.buza.server.entity.TbPost;

public interface TbPostMapper {
    int deleteByPrimaryKey(Integer postId);

    int insert(TbPost record);

    int insertSelective(TbPost record);

    TbPost selectByPrimaryKey(Integer postId);

    int updateByPrimaryKeySelective(TbPost record);

    int updateByPrimaryKey(TbPost record);
}