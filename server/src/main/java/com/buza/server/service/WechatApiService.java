package com.buza.server.service;

import com.buza.server.dto.WechatGoodsDto;

import java.util.List;

public interface WechatApiService {

    public List<WechatGoodsDto> getAllWechatGoods();

}
