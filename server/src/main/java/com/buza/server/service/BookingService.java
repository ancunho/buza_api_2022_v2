package com.buza.server.service;

import com.buza.server.dto.TbBookingDto;
import com.buza.server.entity.TbBooking;

import java.util.List;

public interface BookingService {

    public Boolean insertTbBooking(TbBooking tbBooking);
    public Boolean updateTbBooking(TbBooking tbBooking);
    public List<TbBookingDto> getAllBookingList(TbBookingDto tbBookingDto);
    public TbBookingDto getBookingInfoByBookingId(Integer bookingId);

}
