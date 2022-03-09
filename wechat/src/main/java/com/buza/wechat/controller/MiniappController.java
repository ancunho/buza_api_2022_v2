package com.buza.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.buza.server.common.BaseResponse;
import com.buza.server.common.ResponseCode;
import com.buza.wechat.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/buza/miniapp")
public class MiniappController {

    @RequestMapping(value = "/getOpenIdByCode")
    public BaseResponse getOpenIdOrSessionKeyByCode(@RequestParam(value = "code", required = true) String code) {
        if (code == null || "".equals(code)) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.INSERT_SUCCESS.getCode(), ResponseCode.INSERT_SUCCESS.getDesc());
        }

        JSONObject sessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);

        // 接收微信接口服务 获取返回的参数
        String openId = sessionKeyOpenId.getString("openid");
        String sessionKey = sessionKeyOpenId.getString("session_key");

        log.info("openid:" + openId);
        log.info("sessionKey:" + sessionKey);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("openId", openId);
        resultMap.put("sessionKey", sessionKey);
        return BaseResponse.valueOfSuccess(resultMap);
    }

    @RequestMapping(value = "/getAccessToken")
    public BaseResponse getAccessToken() {
        JSONObject jsonObject = WechatUtil.getAccessToken();
        String accessToken = "";
        accessToken = jsonObject.getString("access_token");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("accessToken", accessToken);

        return BaseResponse.valueOfSuccess(resultMap);
    }

}
