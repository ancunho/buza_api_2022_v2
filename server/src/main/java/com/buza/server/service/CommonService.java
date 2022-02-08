package com.buza.server.service;

import com.buza.server.dto.TbCommonCodeDto;
import com.buza.server.entity.TbCommonCode;

import java.util.List;

public interface CommonService {

    public Boolean insertTbCommonCode(TbCommonCode tbCommonCode);
    public Boolean updateTbCommonCode(TbCommonCode tbCommonCode);
    public List<TbCommonCodeDto> getAllTbCommonCodeList();
    public TbCommonCodeDto getTbCommonCodeInfoByCodeId(Integer codeId);

}
