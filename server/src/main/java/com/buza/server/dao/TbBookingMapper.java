package com.buza.server.dao;

import com.buza.server.entity.TbBooking;

public interface TbBookingMapper {
    int deleteByPrimaryKey(Long bookingId);

    int insert(TbBooking record);

    int insertSelective(TbBooking record);

    TbBooking selectByPrimaryKey(Long bookingId);

    int updateByPrimaryKeySelective(TbBooking record);

    int updateByPrimaryKey(TbBooking record);
}