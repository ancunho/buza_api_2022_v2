package com.buza.server.dao;

import com.buza.server.dto.TbClassificationDto;
import com.buza.server.entity.TbClassification;

import java.util.List;

public interface TbClassificationMapper {
    int deleteByPrimaryKey(Integer classificationId);

    int insert(TbClassification record);

    int insertSelective(TbClassification record);

    TbClassification selectByPrimaryKey(Integer classificationId);

    int updateByPrimaryKeySelective(TbClassification record);

    int updateByPrimaryKey(TbClassification record);

    List<TbClassificationDto> lstTbClassification(TbClassificationDto tbClassificationDto);

    TbClassificationDto getTbClassificationInfoByClassificationId(Integer classificationId);

    List<TbClassificationDto> lstTbClassificationByParentClassificationId(Integer parentClassificationId);
}