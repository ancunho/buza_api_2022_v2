package com.buza.server.service.impl;

import com.buza.server.dao.TbCustomerMapper;
import com.buza.server.dao.TbCustomerShopMapper;
import com.buza.server.dto.TbCustomerDto;
import com.buza.server.dto.TbCustomerShopDto;
import com.buza.server.entity.TbCustomer;
import com.buza.server.entity.TbCustomerShop;
import com.buza.server.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private TbCustomerMapper tbCustomerMapper;

    @Resource
    private TbCustomerShopMapper tbCustomerShopMapper;

    @Transactional
    public Boolean insertTbCustomer(TbCustomer tbCustomer) {
        int insertCount = tbCustomerMapper.insertSelective(tbCustomer);
        if (insertCount > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean updateTbCustomer(TbCustomer tbCustomer) {
        int updateCount = tbCustomerMapper.updateByPrimaryKeySelective(tbCustomer);
        if (updateCount > 0) {
            return true;
        }
        return false;
    }

    public Boolean isExistMobileNo(Map<String, Object> mapParams) {
        Integer isExistMobileNo = tbCustomerMapper.isExistMobileNo(mapParams);
        if (isExistMobileNo == null) {
            return false;
        }
        return true;
    }

    public List<TbCustomerDto> getAllCustomerList() {
        return tbCustomerMapper.getAllCustomerList();
    }

    public TbCustomerDto getCustomerInfoByCustomerId(Integer customerId) {
        return tbCustomerMapper.getCustomerInfoByCustomerId(customerId);
    }

    @Transactional
    public void deleteCustomerByCustomerId(Integer customerId) {
        tbCustomerMapper.deleteByPrimaryKey(customerId);
    }

    @Transactional
    public void insertTbCustomerShop(TbCustomerShop tbCustomerShop) {
        tbCustomerShopMapper.insertSelective(tbCustomerShop);
    }

    public void deleteTbCustomerShopByCustomerId(Integer customerId) {
        tbCustomerShopMapper.deleteByCustomerId(customerId);
    }

    public List<TbCustomerShopDto> getCustomerShopListByCustomerId(Integer customerId) {
        return tbCustomerShopMapper.getCustomerShopListByCustomerId(customerId);
    }

}
