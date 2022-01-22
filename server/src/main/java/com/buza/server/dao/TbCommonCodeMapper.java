package com.buza.server.dao;

import com.buza.server.entity.TbCommonCode;

public interface TbCommonCodeMapper {
    int deleteByPrimaryKey(Integer codeId);

    int insert(TbCommonCode record);

    int insertSelective(TbCommonCode record);

    TbCommonCode selectByPrimaryKey(Integer codeId);

    int updateByPrimaryKeySelective(TbCommonCode record);

    int updateByPrimaryKey(TbCommonCode record);
}