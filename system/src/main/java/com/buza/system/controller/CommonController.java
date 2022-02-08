package com.buza.system.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.buza.server.common.BaseResponse;
import com.buza.server.common.Const;
import com.buza.server.common.ResponseCode;
import com.buza.server.common.ServerResponse;
import com.buza.server.dto.TbCommonCodeDto;
import com.buza.server.entity.TbCommonCode;
import com.buza.server.service.CommonService;
import com.buza.server.util.RedisUtil;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
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
                tbCommonCode.setUseYn(tbCommonCodeDto.getUseYn());
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
                tbCommonCode.setUseYn(tbCommonCodeDto.getUseYn());
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

}
