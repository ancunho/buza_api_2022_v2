package com.buza.system.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.buza.server.common.Const;
import com.buza.server.common.ServerResponse;
import com.buza.server.util.RedisUtil;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Slf4j
@RestController
public class CommonController {

    @Autowired
    public RedisUtil redisUtil;

    @Autowired
    private Producer producer;

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

}
