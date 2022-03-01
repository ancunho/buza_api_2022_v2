package com.buza.server.dao;

import com.buza.server.entity.TbClassification;

public interface TbClassificationMapper {
    int deleteByPrimaryKey(Integer classificationId);

    int insert(TbClassification record);

    int insertSelective(TbClassification record);

    TbClassification selectByPrimaryKey(Integer classificationId);

    int updateByPrimaryKeySelective(TbClassification record);

    int updateByPrimaryKey(TbClassification record);
}