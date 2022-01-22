package com.buza.server.dao;

import com.buza.server.dto.SysRoleDto;
import com.buza.server.entity.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRoleDto> getAllSysRoleList();

    Integer existRoleName(Map<String, Object> mapParams);



}