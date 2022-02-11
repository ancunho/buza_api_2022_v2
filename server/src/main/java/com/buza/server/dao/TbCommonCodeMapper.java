package com.buza.server.dao;

import com.buza.server.dto.TbCommonCodeDto;
import com.buza.server.entity.TbCommonCode;

import java.util.List;

public interface TbCommonCodeMapper {
    int deleteByPrimaryKey(Integer codeId);

    int insert(TbCommonCode record);

    int insertSelective(TbCommonCode record);

    TbCommonCode selectByPrimaryKey(Integer codeId);

    int updateByPrimaryKeySelective(TbCommonCode record);

    int updateByPrimaryKey(TbCommonCode record);

    List<TbCommonCodeDto> getAllTbCommonCodeList();

    TbCommonCodeDto getTbCommonCodeInfoByCodeId(Integer codeId);

    List<TbCommonCodeDto> lstTbCommonCodeByCodeType(String codeType);
}