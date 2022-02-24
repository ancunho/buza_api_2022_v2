package com.buza.system.controller;

import com.buza.server.common.BaseResponse;
import com.buza.server.common.ResponseCode;
import com.buza.server.dto.*;
import com.buza.server.entity.*;
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

    @PostMapping(value = "/spu/proc")
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

    @GetMapping(value = "/sku/info")
    public BaseResponse getTbSkuInfoBySkuId(BaseRequest baseRequest, @RequestParam("skuId") Integer skuId) {
        if (skuId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        TbSkuDto tbSkuDto = skuService.getTbSkuInfoBySkuId(skuId);
        return BaseResponse.valueOfSuccess(tbSkuDto);
    }

    @GetMapping(value = "/sku/listBySpuId")
    public BaseResponse lstTbSkuBySpuId(BaseRequest baseRequest, @RequestParam("spuId") Integer spuId) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbSkuDto> returnData = skuService.lstTbSkuBySpuId(spuId);
        return BaseResponse.valueOfSuccessList(returnData);
    }

    @PostMapping(value = "/sku/proc")
    public BaseResponse procTB_SKU(BaseRequest baseRequest, @RequestBody TbSkuDto tbSkuDto) {
        if (StringUtils.isEmpty(tbSkuDto.getSkuName()) || StringUtils.isEmpty(String.valueOf(tbSkuDto.getSpuId()))) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            if (tbSkuDto.getSkuId() == null || "".equals(String.valueOf(tbSkuDto.getSkuId()))) {
                // insert new
                TbSku tbSku = new TbSku();
                tbSku.setSpuId(tbSkuDto.getSpuId());
                tbSku.setSkuName(tbSkuDto.getSkuName());
                tbSku.setSkuIntro(tbSkuDto.getSkuIntro());
                tbSku.setStatus(tbSkuDto.getStatus());
                tbSku.setOption01(tbSkuDto.getOption01());
                tbSku.setOption02(tbSkuDto.getOption02());
                tbSku.setOption03(tbSkuDto.getOption03());
                tbSku.setOption04(tbSkuDto.getOption04());
                tbSku.setOption05(tbSkuDto.getOption05());

                boolean isSuccessInsert = skuService.insertTbSku(tbSku);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                // update
                TbSku tbSku = new TbSku();
                tbSku.setSkuId(tbSkuDto.getSkuId());
                tbSku.setSpuId(tbSkuDto.getSpuId());
                tbSku.setSkuName(tbSkuDto.getSkuName());
                tbSku.setSkuIntro(tbSkuDto.getSkuIntro());
                tbSku.setStatus(tbSkuDto.getStatus());
                tbSku.setOption01(tbSkuDto.getOption01());
                tbSku.setOption02(tbSkuDto.getOption02());
                tbSku.setOption03(tbSkuDto.getOption03());
                tbSku.setOption04(tbSkuDto.getOption04());
                tbSku.setOption05(tbSkuDto.getOption05());

                boolean isSuccessUpdate = skuService.updateTbSku(tbSku);
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
     * SPU ATTRIBUTE
     ***************************************************************** */

    @PostMapping(value = "/spuAttr/list")
    public BaseResponse lstTbSpuAttribute(BaseRequest baseRequest, @RequestBody TbSpuAttributeDto tbSpuAttributeDto) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbSpuAttributeDto> returnData = skuService.lstTbSpuAttribute(tbSpuAttributeDto);
        return BaseResponse.valueOfSuccessList(returnData);
    }

    @GetMapping(value = "/spuAttr/info")
    public BaseResponse getTbSpuAttributeInfoBySpuAttrId(BaseRequest baseRequest, @RequestParam("spuAttrId") Integer spuAttrId) {
        if (spuAttrId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        TbSpuAttributeDto tbSpuAttributeDto = skuService.getTbSpuAttributeInfoBySpuAttrId(spuAttrId);
        return BaseResponse.valueOfSuccess(tbSpuAttributeDto);
    }

    @GetMapping(value = "/spuAttr/listBySpuId")
    public BaseResponse lstTbSpuAttributeBySpuId(BaseRequest baseRequest, @RequestParam("spuId") Integer spuId) {
        if (spuId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbSpuAttributeDto> returnData = skuService.lstTbSpuAttributeBySpuId(spuId);
        return BaseResponse.valueOfSuccessList(returnData);
    }

    @GetMapping(value = "/spuAttr/listByAttrId")
    public BaseResponse lstTbSpuAttributeByAttrId(BaseRequest baseRequest, @RequestParam("attrId") Integer attrId) {
        if (attrId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbSpuAttributeDto> returnData = skuService.lstTbSpuAttributeByAttrId(attrId);
        return BaseResponse.valueOfSuccessList(returnData);
    }

    @PostMapping(value = "/spuAttr/proc")
    public BaseResponse procTB_SPU_ATTRIBUTE(BaseRequest baseRequest, @RequestBody TbSpuAttributeDto tbSpuAttributeDto) {
        try {
            if (tbSpuAttributeDto.getSpuAttrId() == null || "".equals(String.valueOf(tbSpuAttributeDto.getSpuAttrId()))) {
                // insert new
                TbSpuAttribute tbSpuAttribute = new TbSpuAttribute();
                tbSpuAttribute.setSpuId(tbSpuAttributeDto.getSpuId());
                tbSpuAttribute.setAttrId(tbSpuAttributeDto.getAttrId());
                tbSpuAttribute.setStatus(tbSpuAttributeDto.getStatus());
                tbSpuAttribute.setOption01(tbSpuAttributeDto.getOption01());
                tbSpuAttribute.setOption02(tbSpuAttributeDto.getOption02());
                tbSpuAttribute.setOption03(tbSpuAttributeDto.getOption03());
                tbSpuAttribute.setOption04(tbSpuAttributeDto.getOption04());
                tbSpuAttribute.setOption05(tbSpuAttributeDto.getOption05());

                boolean isSuccessInsert = skuService.insertTbSpuAttribute(tbSpuAttribute);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                // update
                TbSpuAttribute tbSpuAttribute = new TbSpuAttribute();
                tbSpuAttribute.setSpuAttrId(tbSpuAttributeDto.getSpuAttrId());
                tbSpuAttribute.setSpuId(tbSpuAttributeDto.getSpuId());
                tbSpuAttribute.setAttrId(tbSpuAttributeDto.getAttrId());
                tbSpuAttribute.setStatus(tbSpuAttributeDto.getStatus());
                tbSpuAttribute.setOption01(tbSpuAttributeDto.getOption01());
                tbSpuAttribute.setOption02(tbSpuAttributeDto.getOption02());
                tbSpuAttribute.setOption03(tbSpuAttributeDto.getOption03());
                tbSpuAttribute.setOption04(tbSpuAttributeDto.getOption04());
                tbSpuAttribute.setOption05(tbSpuAttributeDto.getOption05());

                boolean isSuccessUpdate = skuService.updateTbSpuAttribute(tbSpuAttribute);
                if (isSuccessUpdate) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.SAVE_ERROR.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
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

    @GetMapping(value = "/skuAttr/info")
    public BaseResponse getTbSkuAttributeBySkuAttrId(BaseRequest baseRequest, @RequestParam("skuAttrId") Integer skuAttrId) {
        if (skuAttrId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        TbSkuAttributeDto tbSkuAttributeDto = skuService.getTbSkuAttributeBySkuAttrId(skuAttrId);
        return BaseResponse.valueOfSuccess(tbSkuAttributeDto);
    }

    @GetMapping(value = "/skuAttr/listBySkuId")
    public BaseResponse lstTbSkuAttributeBySkuId(BaseRequest baseRequest, @RequestParam("skuId") Integer skuId) {
        if (skuId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbSkuAttributeDto> lstTbSkuAttributeBySkuId = skuService.lstTbSkuAttributeBySkuId(skuId);
        return BaseResponse.valueOfSuccessList(lstTbSkuAttributeBySkuId);
    }

    @GetMapping(value = "/skuAttr/listByAttrValueId")
    public BaseResponse lstTbSkuAttributeByAttrValueId(BaseRequest baseRequest, @RequestParam("attrValueId") Integer attrValueId) {
        if (attrValueId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbSkuAttributeDto> lstTbSkuAttributeByAttrValueId = skuService.lstTbSkuAttributeByAttrValueId(attrValueId);
        return BaseResponse.valueOfSuccessList(lstTbSkuAttributeByAttrValueId);
    }

    @PostMapping(value = "/skuAttr/proc")
    public BaseResponse procTB_SKU_ATTRIBUTE(BaseRequest baseRequest, @RequestBody TbSkuAttributeDto tbSkuAttributeDto) {
        try {
            if (tbSkuAttributeDto.getSkuAttrId() == null || "".equals(String.valueOf(tbSkuAttributeDto.getSkuAttrId()))) {
                // insert new
                TbSkuAttribute tbSkuAttribute = new TbSkuAttribute();
                tbSkuAttribute.setSkuId(tbSkuAttributeDto.getSkuId());
                tbSkuAttribute.setAttrValueId(tbSkuAttributeDto.getAttrValueId());
                tbSkuAttribute.setStatus(tbSkuAttributeDto.getStatus());
                tbSkuAttribute.setOption01(tbSkuAttributeDto.getOption01());
                tbSkuAttribute.setOption02(tbSkuAttributeDto.getOption02());
                tbSkuAttribute.setOption03(tbSkuAttributeDto.getOption03());
                tbSkuAttribute.setOption04(tbSkuAttributeDto.getOption04());
                tbSkuAttribute.setOption05(tbSkuAttributeDto.getOption05());

                boolean isSuccessInsert = skuService.insertTbSkuAttribute(tbSkuAttribute);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                // update
                TbSkuAttribute tbSkuAttribute = new TbSkuAttribute();
                tbSkuAttribute.setSkuAttrId(tbSkuAttributeDto.getSkuAttrId());
                tbSkuAttribute.setSkuId(tbSkuAttributeDto.getSkuId());
                tbSkuAttribute.setAttrValueId(tbSkuAttributeDto.getAttrValueId());
                tbSkuAttribute.setStatus(tbSkuAttributeDto.getStatus());
                tbSkuAttribute.setOption01(tbSkuAttributeDto.getOption01());
                tbSkuAttribute.setOption02(tbSkuAttributeDto.getOption02());
                tbSkuAttribute.setOption03(tbSkuAttributeDto.getOption03());
                tbSkuAttribute.setOption04(tbSkuAttributeDto.getOption04());
                tbSkuAttribute.setOption05(tbSkuAttributeDto.getOption05());

                boolean isSuccessUpdate = skuService.updateTbSkuAttribute(tbSkuAttribute);
                if (isSuccessUpdate) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.SAVE_ERROR.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
    }

    /********************************************************************
     * TB_ATTRIBUTE
     ***************************************************************** */

    @PostMapping(value = "/attr/list")
    public BaseResponse lstTbAttribute(BaseRequest baseRequest, @RequestBody TbAttributeDto tbAttributeDto) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbAttributeDto> returnData = skuService.lstTbAttribute(tbAttributeDto);
        return BaseResponse.valueOfSuccessList(returnData);
    }

    @GetMapping(value = "/attr/info")
    public BaseResponse getTbAttributeInfoByAttrId(BaseRequest baseRequest, @RequestParam("attrId") Integer attrId) {
        if (attrId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        TbAttributeDto tbAttributeDto = skuService.getTbAttributeInfoByAttrId(attrId);
        return BaseResponse.valueOfSuccess(tbAttributeDto);
    }

    @PostMapping(value = "/attr/proc")
    public BaseResponse procTB_ATTRIBUTE(BaseRequest baseRequest, @RequestBody TbAttributeDto tbAttributeDto) {
        try {
            if (tbAttributeDto.getAttrId() == null || "".equals(String.valueOf(tbAttributeDto.getAttrId()))) {
                // insert new
                TbAttribute tbAttribute = new TbAttribute();
                tbAttribute.setAttrName(tbAttributeDto.getAttrName());
                tbAttribute.setAttrType(tbAttributeDto.getAttrType());
                tbAttribute.setStatus(tbAttributeDto.getStatus());
                tbAttribute.setOption01(tbAttributeDto.getOption01());
                tbAttribute.setOption02(tbAttributeDto.getOption02());
                tbAttribute.setOption03(tbAttributeDto.getOption03());
                tbAttribute.setOption04(tbAttributeDto.getOption04());
                tbAttribute.setOption05(tbAttributeDto.getOption05());

                boolean isSuccessInsert = skuService.insertTbAttribute(tbAttribute);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                // update
                TbAttribute tbAttribute = new TbAttribute();
                tbAttribute.setAttrId(tbAttributeDto.getAttrId());
                tbAttribute.setAttrName(tbAttributeDto.getAttrName());
                tbAttribute.setAttrType(tbAttributeDto.getAttrType());
                tbAttribute.setStatus(tbAttributeDto.getStatus());
                tbAttribute.setOption01(tbAttributeDto.getOption01());
                tbAttribute.setOption02(tbAttributeDto.getOption02());
                tbAttribute.setOption03(tbAttributeDto.getOption03());
                tbAttribute.setOption04(tbAttributeDto.getOption04());
                tbAttribute.setOption05(tbAttributeDto.getOption05());

                boolean isSuccessUpdate = skuService.updateTbAttribute(tbAttribute);
                if (isSuccessUpdate) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.SAVE_ERROR.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
    }

    /********************************************************************
     * TB_ATTRIBUTE_VALUE
     ***************************************************************** */
    @PostMapping(value = "/attrValue/list")
    public BaseResponse lstTbAttributeValue(BaseRequest baseRequest, @RequestBody TbAttributeValueDto tbAttributeValueDto) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbAttributeValueDto> lstTbAttributeValue = skuService.lstTbAttributeValue(tbAttributeValueDto);
        return BaseResponse.valueOfSuccessList(lstTbAttributeValue);
    }

    @GetMapping(value = "/attrValue/info")
    public BaseResponse getTbAttributeValueInfoByAttrValueId(BaseRequest baseRequest, @RequestParam("attrValueId") Integer attrValueId) {
        if (attrValueId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        TbAttributeValueDto tbAttributeValueDto = skuService.getTbAttributeValueInfoByAttrValueId(attrValueId);
        return BaseResponse.valueOfSuccess(tbAttributeValueDto);
    }

    @PostMapping(value = "/attrValue/proc")
    public BaseResponse procTB_ATTRIBUTE_VALUE(BaseRequest baseRequest, @RequestBody TbAttributeValueDto tbAttributeValueDto) {
        try {
            if (tbAttributeValueDto.getAttrValueId() == null || "".equals(String.valueOf(tbAttributeValueDto.getAttrValueId()))) {
                TbAttributeValue tbAttributeValue = new TbAttributeValue();
                tbAttributeValue.setAttrId(tbAttributeValueDto.getAttrId());
                tbAttributeValue.setAttrValue(tbAttributeValueDto.getAttrValue());
                tbAttributeValue.setPrice(tbAttributeValueDto.getPrice());
                tbAttributeValue.setVipPrice(tbAttributeValueDto.getVipPrice());
                tbAttributeValue.setDeliveryPrice(tbAttributeValueDto.getDeliveryPrice());
                tbAttributeValue.setDeliveryType(tbAttributeValueDto.getDeliveryType());
                tbAttributeValue.setStatus(tbAttributeValueDto.getStatus());
                tbAttributeValue.setOption01(tbAttributeValueDto.getOption01());
                tbAttributeValue.setOption02(tbAttributeValueDto.getOption02());
                tbAttributeValue.setOption03(tbAttributeValueDto.getOption03());
                tbAttributeValue.setOption04(tbAttributeValueDto.getOption04());
                tbAttributeValue.setOption05(tbAttributeValueDto.getOption05());

                boolean isSuccessInsert = skuService.insertTbAttributeValue(tbAttributeValue);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                TbAttributeValue tbAttributeValue = new TbAttributeValue();
                tbAttributeValue.setAttrValueId(tbAttributeValueDto.getAttrValueId());
                tbAttributeValue.setAttrId(tbAttributeValueDto.getAttrId());
                tbAttributeValue.setAttrValue(tbAttributeValueDto.getAttrValue());
                tbAttributeValue.setPrice(tbAttributeValueDto.getPrice());
                tbAttributeValue.setVipPrice(tbAttributeValueDto.getVipPrice());
                tbAttributeValue.setDeliveryPrice(tbAttributeValueDto.getDeliveryPrice());
                tbAttributeValue.setDeliveryType(tbAttributeValueDto.getDeliveryType());
                tbAttributeValue.setStatus(tbAttributeValueDto.getStatus());
                tbAttributeValue.setOption01(tbAttributeValueDto.getOption01());
                tbAttributeValue.setOption02(tbAttributeValueDto.getOption02());
                tbAttributeValue.setOption03(tbAttributeValueDto.getOption03());
                tbAttributeValue.setOption04(tbAttributeValueDto.getOption04());
                tbAttributeValue.setOption05(tbAttributeValueDto.getOption05());

                boolean isSuccessUpdate = skuService.updateTbAttributeValue(tbAttributeValue);
                if (isSuccessUpdate) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.SAVE_ERROR.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
    }

}