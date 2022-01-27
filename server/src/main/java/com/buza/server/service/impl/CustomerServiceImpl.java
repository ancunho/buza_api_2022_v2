package com.buza.server.service.impl;

import com.buza.server.dao.TbCustomerMapper;
import com.buza.server.entity.TbCustomer;
import com.buza.server.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private TbCustomerMapper tbCustomerMapper;

    public Boolean insertTbCustomer(TbCustomer tbCustomer) {
        int insertCount = tbCustomerMapper.insertSelective(tbCustomer);
        if (insertCount > 0) {
            return true;
        }
        return false;
    }

    public Boolean updateTbCustomer(TbCustomer tbCustomer) {
        int updateCount = tbCustomerMapper.updateByPrimaryKeySelective(tbCustomer);
        if (updateCount > 0) {
            return true;
        }
        return false;
    }

}
