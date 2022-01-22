package com.buza.server.dao;

import com.buza.server.dto.SysMenuDto;
import com.buza.server.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    public List<SysMenuDto> getAllSysMenuList();

    Integer existMenuName(Map<String, Object> mapParams);

}