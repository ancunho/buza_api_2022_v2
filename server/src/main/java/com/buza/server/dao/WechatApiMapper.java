package com.buza.server.dao;

import com.buza.server.dto.WechatGoodsDto;

import java.util.List;

public interface WechatApiMapper {

    List<WechatGoodsDto> getAllWechatGoods();

}
