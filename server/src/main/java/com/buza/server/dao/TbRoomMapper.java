package com.buza.server.dao;

import com.buza.server.entity.TbRoom;

public interface TbRoomMapper {
    int deleteByPrimaryKey(Integer roomId);

    int insert(TbRoom record);

    int insertSelective(TbRoom record);

    TbRoom selectByPrimaryKey(Integer roomId);

    int updateByPrimaryKeySelective(TbRoom record);

    int updateByPrimaryKey(TbRoom record);
}