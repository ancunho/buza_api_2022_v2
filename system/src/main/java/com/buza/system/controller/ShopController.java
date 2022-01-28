package com.buza.system.controller;

import com.buza.server.common.BaseResponse;
import com.buza.server.common.ResponseCode;
import com.buza.server.dto.BaseRequest;
import com.buza.server.dto.TbShopDto;
import com.buza.server.entity.TbShop;
import com.buza.server.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping(value = "/proc")
    public BaseResponse procTB_SHOP(BaseRequest baseRequest, @RequestBody TbShopDto tbShopDto) {
        if (StringUtils.isEmpty(tbShopDto.getShopName())) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            if (tbShopDto.getShopId() == null) {
                Map<String, Object> mapParams = new HashMap<>();
                mapParams.put("shopName", tbShopDto.getShopName());
                boolean isExistShopName = shopService.isExistShopName(mapParams);
                if (isExistShopName) {
                    return BaseResponse.valueOfFailureMessage("此门店名已使用");
                }

                TbShop tbShop = new TbShop();
                tbShop.setShopName(tbShopDto.getShopName());
                tbShop.setShopIntro(tbShopDto.getShopIntro());
                tbShop.setManagerName(tbShopDto.getManagerName());
                tbShop.setManagerMobile(tbShopDto.getManagerMobile());
                tbShop.setShopMobile(tbShopDto.getShopMobile());
                tbShop.setShopStatus(tbShopDto.getShopStatus());
                tbShop.setShopProvince(tbShopDto.getShopProvince());
                tbShop.setShopCity(tbShopDto.getShopCity());
                tbShop.setShopDistrict(tbShopDto.getShopDistrict());
                tbShop.setShopAddress(tbShopDto.getShopAddress());
                tbShop.setOption01(tbShopDto.getOption01());
                tbShop.setOption02(tbShopDto.getOption02());
                tbShop.setOption03(tbShopDto.getOption03());
                tbShop.setOption04(tbShopDto.getOption04());
                tbShop.setOption05(tbShopDto.getOption05());

                boolean isSuccessInsert = shopService.insertTbShop(tbShop);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                Map<String, Object> mapParams = new HashMap<>();
                mapParams.put("shopId", tbShopDto.getShopId());
                mapParams.put("shopName", tbShopDto.getShopName());
                boolean isExistShopName = shopService.isExistShopName(mapParams);
                if (isExistShopName) {
                    return BaseResponse.valueOfFailureMessage("此门店名已使用");
                }

                TbShop tbShop = new TbShop();
                tbShop.setShopId(tbShopDto.getShopId());
                tbShop.setShopName(tbShopDto.getShopName());
                tbShop.setShopIntro(tbShopDto.getShopIntro());
                tbShop.setManagerName(tbShopDto.getManagerName());
                tbShop.setManagerMobile(tbShopDto.getManagerMobile());
                tbShop.setShopMobile(tbShopDto.getShopMobile());
                tbShop.setShopStatus(tbShopDto.getShopStatus());
                tbShop.setShopProvince(tbShopDto.getShopProvince());
                tbShop.setShopCity(tbShopDto.getShopCity());
                tbShop.setShopDistrict(tbShopDto.getShopDistrict());
                tbShop.setShopAddress(tbShopDto.getShopAddress());
                tbShop.setOption01(tbShopDto.getOption01());
                tbShop.setOption02(tbShopDto.getOption02());
                tbShop.setOption03(tbShopDto.getOption03());
                tbShop.setOption04(tbShopDto.getOption04());
                tbShop.setOption05(tbShopDto.getOption05());

                boolean isSuccessUpdate = shopService.updateTbCustomer(tbShop);
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
