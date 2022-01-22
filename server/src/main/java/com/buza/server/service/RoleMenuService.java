package com.buza.server.service;

import com.buza.server.dto.SysMenuDto;
import com.buza.server.dto.SysRoleDto;
import com.buza.server.dto.SysRoleMenuDto;
import com.buza.server.dto.SysUserDto;
import com.buza.server.entity.SysMenu;
import com.buza.server.entity.SysRole;
import com.buza.server.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface RoleMenuService {

    // 菜单相关 //
    public List<SysMenuDto> getAllSysMenuList();
    public Boolean existMenuName(Map<String, Object> mapParams);
    public Boolean insertSysMenu(SysMenu sysMenu);
    public Boolean updateSysMenu(SysMenu sysMenu);

    // 权限相关 //
    public List<SysRoleDto> getAllSysRoleList();
    public Boolean existRoleName(Map<String, Object> mapParams);
    public Boolean insertSysRole(SysRole sysRole);
    public Boolean updateSysRole(SysRole sysRole);

    // 权限菜单相关 //
    public List<SysRoleMenuDto> getSysRoleMenuByRoleId(Integer roleId);
    public void deleteSysRoleMenuByRoleId(Integer roleId);
    public Boolean insertSysRoleMenuByRoleIdMenuIds(Map<String, Object> mapParams);

    // 用户相关 //
    List<SysUserDto> getAllSysUserList();
    public Boolean existUserName(Map<String, Object> mapParams);
    public Boolean insertSysUser(SysUser sysUser);
    public Boolean updateSysUser(SysUser sysUser);
    List<SysUserDto> getRoleListByUserSeq(Integer userSeq);
    public void deleteSysUserRoleByUserSeq(Integer userSeq);

    public Boolean insertSysUserRoleByUserSeqRoleIds(Map<String, Object> mapParams);
}
