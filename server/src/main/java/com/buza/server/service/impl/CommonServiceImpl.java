package com.buza.server.service.impl;

import com.buza.server.common.BaseResponse;
import com.buza.server.common.ResponseCode;
import com.buza.server.dao.TbCommonCodeMapper;
import com.buza.server.dto.TbCommonCodeDto;
import com.buza.server.entity.TbCommonCode;
import com.buza.server.service.CommonService;
import com.buza.server.util.DateUtil;
import com.buza.server.util.PropertiesUtils;
import com.buza.server.util.ValueUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    private TbCommonCodeMapper tbCommonCodeMapper;

    @Transactional
    public Boolean insertTbCommonCode(TbCommonCode tbCommonCode) {
        int insertCount = tbCommonCodeMapper.insertSelective(tbCommonCode);
        if (insertCount > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean updateTbCommonCode(TbCommonCode tbCommonCode) {
        int updateCount = tbCommonCodeMapper.updateByPrimaryKeySelective(tbCommonCode);
        if (updateCount > 0) {
            return true;
        }
        return false;
    }

    public List<TbCommonCodeDto> getAllTbCommonCodeList() {
        return tbCommonCodeMapper.getAllTbCommonCodeList();
    }

    public TbCommonCodeDto getTbCommonCodeInfoByCodeId(Integer codeId) {
        return tbCommonCodeMapper.getTbCommonCodeInfoByCodeId(codeId);
    }

    public List<TbCommonCodeDto> lstTbCommonCodeByCodeType(String codeType) {
        return tbCommonCodeMapper.lstTbCommonCodeByCodeType(codeType);
    }

    public String saveSingleImage(MultipartFile multipartFile) {
        //기존파일명
        String fileName = multipartFile.getOriginalFilename();
        //파일확장자
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //새로운 파일명  UUID.randomUUID().toString()
        String uploadFileName = DateUtil.getTime() + "_" + ValueUtil.generateUid(10) + "." + fileExtensionName;
        //文件路径 + "/"
        String remotePath = File.separator +  DateUtil.getDays() + File.separator;
        //文件保存路径   /home
        String path = PropertiesUtils.getFilePath() + remotePath;

        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{},文件格式：{}",fileName,path,uploadFileName, fileExtensionName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        FileInputStream fileInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {

            fileInputStream = (FileInputStream) multipartFile.getInputStream();
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path + File.separator + uploadFileName));

            byte[] bs = new byte[1024];
            int len;

            while ((len = fileInputStream.read(bs)) != -1) {
                bufferedOutputStream.write(bs, 0, len);
            }

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String finalFileName = remotePath + uploadFileName;
        return finalFileName;
    }


}
