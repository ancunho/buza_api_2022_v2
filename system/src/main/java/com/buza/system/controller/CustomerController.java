package com.buza.system.controller;

import com.buza.server.common.BaseResponse;
import com.buza.server.common.ResponseCode;
import com.buza.server.dto.BaseRequest;
import com.buza.server.dto.TbCustomerDto;
import com.buza.server.entity.TbCustomer;
import com.buza.server.service.CustomerService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 회원추가 혹은 편집
     * @param baseRequest
     * @param tbCustomerDto
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/proc")
    public BaseResponse procTB_CUSTOMER(BaseRequest baseRequest, @RequestBody TbCustomerDto tbCustomerDto) {
        if (StringUtils.isEmpty(tbCustomerDto.getMobileNo())) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            if (tbCustomerDto.getCustomerId() == null) {
                Map<String, Object> mapParams = new HashMap<>();
                mapParams.put("mobileNo", tbCustomerDto.getMobileNo());
                boolean isExistMobileNo = customerService.isExistMobileNo(mapParams);
                if (isExistMobileNo) {
                    return BaseResponse.valueOfFailureMessage("此手机号已注册");
                }

                TbCustomer tbCustomer = new TbCustomer();
                tbCustomer.setUsername(tbCustomerDto.getUsername());
                if (StringUtils.isEmpty(tbCustomerDto.getPassword())) {
                    tbCustomer.setPassword(new BCryptPasswordEncoder().encode("g12345678"));
                } else {
                    tbCustomer.setPassword(new BCryptPasswordEncoder().encode(tbCustomerDto.getPassword()));
                }
                tbCustomer.setMobileNo(tbCustomerDto.getMobileNo());
                tbCustomer.setRealname(tbCustomerDto.getRealname());
                tbCustomer.setWeixinOpenId(tbCustomerDto.getWeixinOpenId());
                tbCustomer.setWeixinUnionId(tbCustomerDto.getWeixinUnionId());
                tbCustomer.setNickname(tbCustomerDto.getNickname());
                tbCustomer.setCountry(tbCustomerDto.getCountry());
                tbCustomer.setCity(tbCustomerDto.getCity());
                tbCustomer.setAddress(tbCustomerDto.getAddress());
                tbCustomer.setBirthday(tbCustomerDto.getBirthday());
                tbCustomer.setEmail(tbCustomerDto.getEmail());
                tbCustomer.setCustomerType(tbCustomerDto.getCustomerType());
                tbCustomer.setGrade(tbCustomerDto.getGrade());
                tbCustomer.setSex(tbCustomerDto.getSex());
                tbCustomer.setStatus(tbCustomerDto.getStatus());
                tbCustomer.setOption01(tbCustomerDto.getOption01());
                tbCustomer.setOption02(tbCustomerDto.getOption02());
                tbCustomer.setOption03(tbCustomerDto.getOption03());
                tbCustomer.setOption04(tbCustomerDto.getOption04());
                tbCustomer.setOption05(tbCustomerDto.getOption05());

                boolean isSuccessInsert = customerService.insertTbCustomer(tbCustomer);
                if (isSuccessInsert) {
                    return BaseResponse.valueOfSuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
                }
                return BaseResponse.valueOfFailureMessage(ResponseCode.INSERT_ERROR.getDesc());
            } else {
                Map<String, Object> mapParams = new HashMap<>();
                mapParams.put("customerId", tbCustomerDto.getCustomerId());
                mapParams.put("mobileNo", tbCustomerDto.getMobileNo());
                boolean isExistMobileNo = customerService.isExistMobileNo(mapParams);
                if (isExistMobileNo) {
                    return BaseResponse.valueOfFailureMessage("此手机号已注册");
                }

                TbCustomer tbCustomer = new TbCustomer();
                tbCustomer.setUsername(tbCustomerDto.getUsername());
                if (StringUtils.isEmpty(tbCustomerDto.getPassword())) {
                    tbCustomer.setPassword(new BCryptPasswordEncoder().encode("g12345678"));
                } else {
                    tbCustomer.setPassword(new BCryptPasswordEncoder().encode(tbCustomerDto.getPassword()));
                }
                tbCustomer.setMobileNo(tbCustomerDto.getMobileNo());
                tbCustomer.setRealname(tbCustomerDto.getRealname());
                tbCustomer.setWeixinOpenId(tbCustomerDto.getWeixinOpenId());
                tbCustomer.setWeixinUnionId(tbCustomerDto.getWeixinUnionId());
                tbCustomer.setNickname(tbCustomerDto.getNickname());
                tbCustomer.setCountry(tbCustomerDto.getCountry());
                tbCustomer.setCity(tbCustomerDto.getCity());
                tbCustomer.setAddress(tbCustomerDto.getAddress());
                tbCustomer.setBirthday(tbCustomerDto.getBirthday());
                tbCustomer.setEmail(tbCustomerDto.getEmail());
                tbCustomer.setCustomerType(tbCustomerDto.getCustomerType());
                tbCustomer.setGrade(tbCustomerDto.getGrade());
                tbCustomer.setSex(tbCustomerDto.getSex());
                tbCustomer.setStatus(tbCustomerDto.getStatus());
                tbCustomer.setOption01(tbCustomerDto.getOption01());
                tbCustomer.setOption02(tbCustomerDto.getOption02());
                tbCustomer.setOption03(tbCustomerDto.getOption03());
                tbCustomer.setOption04(tbCustomerDto.getOption04());
                tbCustomer.setOption05(tbCustomerDto.getOption05());

                boolean isSuccessUpdate = customerService.updateTbCustomer(tbCustomer);
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

    /**
     * 회원리스트
     * @param baseRequest
     * @return
     */
    @GetMapping(value = "/list")
    public BaseResponse getAllCustomerList(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbCustomerDto> returnData = customerService.getAllCustomerList();
        return BaseResponse.valueOfSuccessList(returnData);
    }

    /**
     * 회원상세정보
     * @param customerId
     * @return
     */
    @GetMapping(value = "/info")
    public BaseResponse getCustomerInfoByCustomerId(@RequestParam("customerId") Integer customerId) {
        if (customerId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        TbCustomerDto tbCustomerDto = customerService.getCustomerInfoByCustomerId(customerId);
        return BaseResponse.valueOfSuccess(tbCustomerDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/delete")
    public BaseResponse deleteCustomerByCustomerId(@RequestParam("customerId") Integer customerId) {
        if (customerId == null) {
            return BaseResponse.valueOfFailureCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            customerService.deleteCustomerByCustomerId(customerId);
            return BaseResponse.valueOfSuccessMessage(ResponseCode.DELETE_SUCCESS.getDesc());
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.valueOfFailureMessage(ResponseCode.DELETE_ERROR.getDesc());
        }
    }
}
