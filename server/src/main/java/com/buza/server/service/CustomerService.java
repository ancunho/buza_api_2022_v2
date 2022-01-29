package com.buza.server.service;

import com.buza.server.dto.TbCustomerDto;
import com.buza.server.dto.TbCustomerShopDto;
import com.buza.server.entity.TbCustomer;
import com.buza.server.entity.TbCustomerShop;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    public Boolean insertTbCustomer(TbCustomer tbCustomer);
    public Boolean updateTbCustomer(TbCustomer tbCustomer);
    public Boolean isExistMobileNo(Map<String, Object> mapParams);

    List<TbCustomerDto> getAllCustomerList();
    TbCustomerDto getCustomerInfoByCustomerId(Integer customerId);

    public void deleteCustomerByCustomerId(Integer customerId);

    void insertTbCustomerShop(TbCustomerShop tbCustomerShop);

    void deleteTbCustomerShopByCustomerId(Integer customerId);

    List<TbCustomerShopDto> getCustomerShopListByCustomerId(Integer customerId);
}
