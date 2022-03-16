package com.buza.wechat.controller;

import com.buza.server.common.BaseResponse;
import com.buza.server.dto.BaseRequest;
import com.buza.server.service.WechatApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/miniapp/goods")
public class MiniappGoodsController {

    @Autowired
    private WechatApiService wechatApiService;

    @PostMapping(value = "/list")
    public BaseResponse getAllWechatGoods(BaseRequest baseRequest) {
        return BaseResponse.valueOfSuccess(wechatApiService.getAllWechatGoods());
    }
}
