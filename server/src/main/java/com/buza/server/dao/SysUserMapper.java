package com.buza.server.dao;

import com.buza.server.dto.SysUserDto;
import com.buza.server.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer userSeq);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userSeq);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUserDto> getAllSysUserList();

    Integer existUserName(Map<String, Object> mapParams);

    List<SysUserDto> getRoleListByUserSeq(Integer userSeq);
}