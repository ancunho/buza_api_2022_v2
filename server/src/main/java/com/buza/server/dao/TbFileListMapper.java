package com.buza.server.dao;

import com.buza.server.dto.TbFileListDto;
import com.buza.server.entity.TbFileList;

import java.util.List;

public interface TbFileListMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(TbFileList record);

    int insertSelective(TbFileList record);

    TbFileList selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(TbFileList record);

    int updateByPrimaryKey(TbFileList record);

    List<TbFileListDto> getAllTbFileList(TbFileListDto tbFileListDto);
}