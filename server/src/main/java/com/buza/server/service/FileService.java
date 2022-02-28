package com.buza.server.service;

import com.buza.server.dto.TbFileListDto;
import com.buza.server.entity.TbFileList;

import java.util.List;

public interface FileService {

    public Boolean insertTbFileList(TbFileList tbFileList);
    public Boolean updateTbFileList(TbFileList tbFileList);
    public List<TbFileListDto> getAllTbFileList(TbFileListDto tbFileListDto);
}
