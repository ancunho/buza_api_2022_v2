package com.buza.system.controller;

import com.buza.server.common.BaseResponse;
import com.buza.server.common.ResponseCode;
import com.buza.server.dto.BaseRequest;
import com.buza.server.dto.TbSkuAttributeDto;
import com.buza.server.dto.TbSkuDto;
import com.buza.server.dto.TbSpuDto;
import com.buza.server.entity.TbSpu;
import com.buza.server.service.SkuService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class SkuController {

    @Autowired
    private SkuService skuService;

    /********************************************************************
     * SPU
     ***************************************************************** */

    @PostMapping(value = "/spu/list")
    public BaseResponse lstTbSpuDto(BaseRequest baseRequest, @RequestBody TbSpuDto tbSpuDto) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbSpuDto> returnData = skuService.lstTbSpu(tbSpuDto);
        return BaseResponse.valueOfSuccessList(returnData);
    }

    @GetMapping(value = "/spu/info")
    public BaseResponse getTbSpuInfoBySpuId(BaseRequest baseRequest, @RequestParam("spuId") Integer spuId) {
        if (spuId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        TbSpuDto tbSpuDto = skuService.getTbSpuInfoBySpuId(spuId);
        return BaseResponse.valueOfSuccess(tbSpuDto);
    }

    public BaseResponse procTB_SPU(BaseRequest baseRequest, @RequestBody TbSpuDto tbSpuDto) {
        if (StringUtils.isEmpty(tbSpuDto.getSpuName())) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            if (tbSpuDto.getSpuId() == null || "".equals(String.valueOf(tbSpuDto.getSpuId()))) {
                // insert new
                TbSpu tbSpu = new TbSpu();
                tbSpu.setCategoryId(tbSpuDto.getCategoryId());
                tbSpu.setBrandId(tbSpuDto.getBrandId());
                tbSpu.setSpuName(tbSpuDto.getSpuName());
                tbSpu.setSpuType(tbSpuDto.getSpuType());
                tbSpu.setStatus(tbSpuDto.getStatus());
                tbSpu.setOption01(tbSpuDto.getOption01());
                tbSpu.setOption02(tbSpuDto.getOption02());
                tbSpu.setOption03(tbSpuDto.getOption03());
                tbSpu.setOption04(tbSpuDto.getOption04());
                tbSpu.setOption05(tbSpuDto.getOption05());

                boolean isSuccessInsert = skuService.insertTbSpu(tbSpu);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                // update
                TbSpu tbSpu = new TbSpu();
                tbSpu.setSpuId(tbSpuDto.getSpuId());
                tbSpu.setCategoryId(tbSpuDto.getCategoryId());
                tbSpu.setBrandId(tbSpuDto.getBrandId());
                tbSpu.setSpuName(tbSpuDto.getSpuName());
                tbSpu.setSpuType(tbSpuDto.getSpuType());
                tbSpu.setStatus(tbSpuDto.getStatus());
                tbSpu.setOption01(tbSpuDto.getOption01());
                tbSpu.setOption02(tbSpuDto.getOption02());
                tbSpu.setOption03(tbSpuDto.getOption03());
                tbSpu.setOption04(tbSpuDto.getOption04());
                tbSpu.setOption05(tbSpuDto.getOption05());

                boolean isSuccessUpdate = skuService.updateTbSpu(tbSpu);
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


    /********************************************************************
     * SKU
     ***************************************************************** */

    @PostMapping(value = "/sku/list")
    public BaseResponse lstTbSkuDto(BaseRequest baseRequest, @RequestBody TbSkuDto tbSkuDto) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbSkuDto> returnData = skuService.lstTbSku(tbSkuDto);
        return BaseResponse.valueOfSuccessList(returnData);
    }

    /********************************************************************
     * SKU ATTRIBUTE
     ***************************************************************** */

    @PostMapping(value = "/skuAttr/list")
    public BaseResponse lstTbSkuAttribute(BaseRequest baseRequest, @RequestBody TbSkuAttributeDto tbSkuAttributeDto) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbSkuAttributeDto> returnData = skuService.lstTbSkuAttribute(tbSkuAttributeDto);
        return BaseResponse.valueOfSuccessList(returnData);
    }























}
