package com.buza.system.controller;

import com.buza.server.common.BaseResponse;
import com.buza.server.common.Const;
import com.buza.server.common.ResponseCode;
import com.buza.server.dto.BaseRequest;
import com.buza.server.dto.TbFileListDto;
import com.buza.server.service.AliyunService;
import com.buza.server.service.FileService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/file/handle")
public class FileController {

    @Autowired
    private AliyunService aliyunService;

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    public Map<String, Object> file_upload_return_url(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<String> imgList = new ArrayList<>();
//        for (MultipartFile file : multipartFiles) {
        //Const.UPLOAD_IMAGE_MAX_SIZE : 2MB
        if (file.getSize() > 0 && file.getSize() <= (Const.UPLOAD_IMAGE_MAX_SIZE * 20)) {
            String file_path_url = aliyunService.uploadFileReturnURL(file);
            imgList.add(file_path_url);
            result.put("errno", 0);
            result.put("data", imgList);
        } else {
            result.put("errno", 99);
            result.put("data", imgList);
        }
//        }
        return result;
    }

    @PostMapping(value = "/single/image/upload")
    public Map<String, Object> image_upload_return_url(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        Map<String, Object> result = new HashMap<>();
        //Const.UPLOAD_IMAGE_MAX_SIZE : 2MB
        Map<String, Object> imageMap = new HashMap<>();
        if (file.getSize() > 0 && file.getSize() <= (Const.UPLOAD_IMAGE_MAX_SIZE * 20)) {
            imageMap = aliyunService.uploadImageReturnURL(file);
            result.put("errno", 0);
            result.put("data", imageMap);
        } else {
            result.put("errno", 99);
            result.put("data", imageMap);
        }
        return result;
    }

    @PostMapping(value = "/single/image/delete")
    public BaseResponse image_delete(HttpServletRequest request, @RequestParam("imageUrl") String imageUrl) throws Exception {
        if (imageUrl == null || "".equals(imageUrl)) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("imageUrl", imageUrl);
        aliyunService.deleteImage(mapParams);
        return BaseResponse.valueOfSuccessMessage("删除成功");
    }

    @PostMapping(value = "/list")
    public BaseResponse getAllTbFileList(BaseRequest baseRequest, @RequestBody TbFileListDto tbFileListDto) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbFileListDto> returnData = fileService.getAllTbFileList(tbFileListDto);
        return BaseResponse.valueOfSuccessList(returnData);
    }

}
