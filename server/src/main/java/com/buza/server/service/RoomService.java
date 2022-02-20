package com.buza.server.service;

import com.buza.server.dto.TbRoomDto;
import com.buza.server.entity.TbRoom;

import java.util.List;

public interface RoomService {

    public Boolean insertTbRoom(TbRoom tbRoom);
    public Boolean updateTbRoom(TbRoom tbRoom);
    public List<TbRoomDto> getAllRoomList(TbRoomDto tbRoomDto);
    public TbRoomDto getTbRoomInfoByRoomId(Integer roomId);

}
