package com.buza.system.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.buza.server.common.BaseResponse;
import com.buza.server.common.Const;
import com.buza.server.common.ResponseCode;
import com.buza.server.common.ServerResponse;
import com.buza.server.dto.BaseRequest;
import com.buza.server.dto.TbCommonCodeDto;
import com.buza.server.entity.TbCommonCode;
import com.buza.server.service.AliyunService;
import com.buza.server.service.CommonService;
import com.buza.server.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CommonController {

    @Autowired
    public RedisUtil redisUtil;

    @Autowired
    private Producer producer;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AliyunService aliyunService;

    @GetMapping("/captcha")
    public ServerResponse captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String captchaCode = producer.createText();
        String captchaKey = UUID.randomUUID().toString();

        // For Test
//        code = "code111";
//        captchaKey="key111";

        BufferedImage image = producer.createImage(captchaCode);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());

        // Redis에 저장
        redisUtil.hset(Const.CAPTCHA_KEY, captchaKey, captchaCode, 120);
        log.info("验证码 -- {} - {}", captchaKey, captchaCode);

        return ServerResponse.createBySuccess(
                MapUtil.builder()
                        .put("captchaCode", captchaCode)
                        .put("captchaKey", captchaKey)
                        .put("base64Img", base64Img)
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/code/proc")
    public BaseResponse modifyTbCommonCode(HttpServletRequest request, @RequestBody TbCommonCodeDto tbCommonCodeDto) {
        if (StringUtils.isEmpty(tbCommonCodeDto.getCodeName())
            || StringUtils.isEmpty(tbCommonCodeDto.getCodeCd())
        ) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            if (tbCommonCodeDto.getCodeId() == null || String.valueOf(tbCommonCodeDto.getCodeId()) == "") {
                // insert New
                TbCommonCode tbCommonCode = new TbCommonCode();
                tbCommonCode.setCodeType(tbCommonCodeDto.getCodeType());
                tbCommonCode.setCodeCd(tbCommonCodeDto.getCodeCd());
                tbCommonCode.setCodeName(tbCommonCodeDto.getCodeName());
                tbCommonCode.setUseYn(tbCommonCodeDto.getStatus());
                tbCommonCode.setRemark(tbCommonCodeDto.getRemark());
                tbCommonCode.setOption01(tbCommonCodeDto.getOption01());
                tbCommonCode.setOption02(tbCommonCodeDto.getOption02());
                tbCommonCode.setOption03(tbCommonCodeDto.getOption03());
                tbCommonCode.setOption04(tbCommonCodeDto.getOption04());
                tbCommonCode.setOption05(tbCommonCodeDto.getOption05());

                boolean isSuccessInsert = commonService.insertTbCommonCode(tbCommonCode);

                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                // update
                TbCommonCode tbCommonCode = new TbCommonCode();
                tbCommonCode.setCodeId(tbCommonCodeDto.getCodeId());
                tbCommonCode.setCodeType(tbCommonCodeDto.getCodeType());
                tbCommonCode.setCodeCd(tbCommonCodeDto.getCodeCd());
                tbCommonCode.setCodeName(tbCommonCodeDto.getCodeName());
                tbCommonCode.setUseYn(tbCommonCodeDto.getStatus());
                tbCommonCode.setRemark(tbCommonCodeDto.getRemark());
                tbCommonCode.setOption01(tbCommonCodeDto.getOption01());
                tbCommonCode.setOption02(tbCommonCodeDto.getOption02());
                tbCommonCode.setOption03(tbCommonCodeDto.getOption03());
                tbCommonCode.setOption04(tbCommonCodeDto.getOption04());
                tbCommonCode.setOption05(tbCommonCodeDto.getOption05());

                boolean isSuccessUpdate = commonService.updateTbCommonCode(tbCommonCode);

                if (isSuccessUpdate) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.SAVE_ERROR.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.valueOfFailureMessage(ResponseCode.ERROR.getDesc());
        }
    }

    @GetMapping(value = "/code/list")
    public BaseResponse getAllTbCommonCodeList(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbCommonCodeDto> returnData = commonService.getAllTbCommonCodeList();
        return BaseResponse.valueOfSuccessList(returnData);
    }

    @GetMapping(value = "/code/info")
    public BaseResponse getTbCommonCodeInfoByCodeId(@RequestParam("codeId") Integer codeId) {
        if (codeId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        TbCommonCodeDto tbCommonCodeDto = commonService.getTbCommonCodeInfoByCodeId(codeId);
        return BaseResponse.valueOfSuccess(tbCommonCodeDto);
    }

    @GetMapping(value = "/code/type/list")
    public BaseResponse lstTbCommonCodeByCodeType(@RequestParam("codeType") String codeType) {
        if (codeType == null || StringUtils.isEmpty(codeType)) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        List<TbCommonCodeDto> lstTbCommonCodeByCodeType = commonService.lstTbCommonCodeByCodeType(codeType);
        return BaseResponse.valueOfSuccess(lstTbCommonCodeByCodeType);
    }

    @PostMapping(value = "/upload/file")
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

    @PostMapping(value = "/upload/image")
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

    @PostMapping(value = "/upload/image/delete")
    public BaseResponse image_delete(HttpServletRequest request) throws Exception {
        if (request.getAttribute("imageUrl") == null || "".equals(request.getAttribute("imageUrl"))) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("imageUrl", request.getAttribute("imageUrl"));
        aliyunService.deleteImage(mapParams);
        return BaseResponse.valueOfSuccessMessage("删除成功");
    }



}
