package com.buza.server.service;

import com.buza.server.entity.TbCustomer;

public interface CustomerService {

    public Boolean insertTbCustomer(TbCustomer tbCustomer);
    public Boolean updateTbCustomer(TbCustomer tbCustomer);

}
