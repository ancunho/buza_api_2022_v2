package com.buza.system.controller;

import com.buza.server.common.ResponseCode;
import com.buza.server.common.ServerResponse;
import com.buza.server.dto.SysMenuDto;
import com.buza.server.dto.SysRoleDto;
import com.buza.server.dto.SysRoleMenuDto;
import com.buza.server.dto.SysUserDto;
import com.buza.server.entity.SysMenu;
import com.buza.server.entity.SysRole;
import com.buza.server.entity.SysUser;
import com.buza.server.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/config")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 메뉴 리스트 가져오기
     * @return
     */
    @GetMapping("/menu/list")
    public ServerResponse<List<SysMenuDto>> getAllSysMenuList() {
        List<SysMenuDto> returnData = roleMenuService.getAllSysMenuList();
        return ServerResponse.createBySuccess(returnData);
    }

    /**
     * 메뉴편집
     * 메뉴ID가 없으면 -> 신규추가
     * 메뉴ID가 있으면 -> 수정/편집
     * @param sysMenuDto
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/menu/modify")
    public ServerResponse modifySysMenu(@RequestBody SysMenuDto sysMenuDto) {
        if (StringUtils.isEmpty(sysMenuDto.getName())
            || sysMenuDto.getType() == null
            || StringUtils.isEmpty(String.valueOf(sysMenuDto.getType()))
            || StringUtils.isEmpty(sysMenuDto.getStatus())
        ) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        if (sysMenuDto.getId() == null || String.valueOf(sysMenuDto.getId()) == "") {
            // insert New
            // 1. check menu name repeat
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("menuName", sysMenuDto.getName());
            mapParams.put("menuPerms", sysMenuDto.getPerms());
            boolean isExistMenuName = roleMenuService.existMenuName(mapParams);
            if (isExistMenuName) {
                return ServerResponse.createByErrorMessage("菜单名重复, 请使用其他菜单名称");
            }

            // 2. SysMenu 저장
            SysMenu sysMenu = new SysMenu();
            sysMenu.setName(sysMenuDto.getName());
            sysMenu.setParentId((sysMenuDto.getParentId() == null || String.valueOf(sysMenuDto.getParentId()) == "") ? 0 : sysMenuDto.getParentId());
            sysMenu.setStatus(sysMenuDto.getStatus());
            sysMenu.setPath(sysMenuDto.getPath());
            sysMenu.setPerms(sysMenuDto.getPerms());
            sysMenu.setComponent(sysMenuDto.getComponent());
            sysMenu.setType(sysMenuDto.getType());
            sysMenu.setIcon(sysMenuDto.getIcon());
            sysMenu.setOrderNum(sysMenuDto.getOrderNum());
            sysMenu.setOption01(sysMenuDto.getOption01());
            sysMenu.setOption02(sysMenuDto.getOption02());
            sysMenu.setOption03(sysMenuDto.getOption03());
            sysMenu.setOption04(sysMenuDto.getOption04());
            sysMenu.setOption05(sysMenuDto.getOption05());
            boolean isSuccessInsert = roleMenuService.insertSysMenu(sysMenu);
            if (isSuccessInsert) {
                return ServerResponse.createBySuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
            }
            return ServerResponse.createByErrorMessage(ResponseCode.INSERT_ERROR.getDesc());
        } else {
            // modify origin
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("menuId", sysMenuDto.getId());
            mapParams.put("menuName", sysMenuDto.getName());
            mapParams.put("menuPerms", sysMenuDto.getPerms());
            boolean isExistMenuName = roleMenuService.existMenuName(mapParams);
            if (isExistMenuName) {
                return ServerResponse.createByErrorMessage("菜单名重复, 请使用其他菜单名称");
            }

            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(sysMenuDto.getId());
            sysMenu.setName(sysMenuDto.getName());
            sysMenu.setParentId((sysMenuDto.getParentId() == null || String.valueOf(sysMenuDto.getParentId()) == "") ? 0 : sysMenuDto.getParentId());
            sysMenu.setStatus(sysMenuDto.getStatus());
            sysMenu.setPath(sysMenuDto.getPath());
            sysMenu.setPerms(sysMenuDto.getPerms());
            sysMenu.setComponent(sysMenuDto.getComponent());
            sysMenu.setType(sysMenuDto.getType());
            sysMenu.setIcon(sysMenuDto.getIcon());
            sysMenu.setOrderNum(sysMenuDto.getOrderNum());
            sysMenu.setOption01(sysMenuDto.getOption01());
            sysMenu.setOption02(sysMenuDto.getOption02());
            sysMenu.setOption03(sysMenuDto.getOption03());
            sysMenu.setOption04(sysMenuDto.getOption04());
            sysMenu.setOption05(sysMenuDto.getOption05());

            boolean isSuccessUpdate = roleMenuService.updateSysMenu(sysMenu);
            if (isSuccessUpdate) {
                return ServerResponse.createBySuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
            }
            return ServerResponse.createByErrorMessage(ResponseCode.SAVE_ERROR.getDesc());
        }
    }

    /**
     * 권한 리스트가져오기
     * @return
     */
    @GetMapping("/role/list")
    public ServerResponse<List<SysRoleDto>> getAllSysRoleList() {
        List<SysRoleDto> returnData = roleMenuService.getAllSysRoleList();
        return ServerResponse.createBySuccess(returnData);
    }

    /**
     * 권한 편집
     * 권한ID가 없으면 -> 신규추가
     * 권한ID가 있으면 -> 수정/편집
     * @param sysRoleDto
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/role/modify")
    public ServerResponse modifySysRole(@RequestBody SysRoleDto sysRoleDto) {
        if (StringUtils.isEmpty(sysRoleDto.getName())
            || StringUtils.isEmpty(sysRoleDto.getCode())
            || StringUtils.isEmpty(sysRoleDto.getRemark())
            || StringUtils.isEmpty(sysRoleDto.getStatus())
        ) {
            return ServerResponse.createByErrorMessage("权限名称,Code,备注,状态是必填项,请确认！");
        }

        if (sysRoleDto.getId() == null || String.valueOf(sysRoleDto.getId()) == "") {
            // insert New
            // 1. check role name repeat
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("roleName", sysRoleDto.getName());
            mapParams.put("roleCode", sysRoleDto.getCode());
            boolean isExistRoleName = roleMenuService.existRoleName(mapParams);
            if (isExistRoleName) {
                return ServerResponse.createByErrorMessage("权限名重复, 请使用其他权限名");
            }

            // 2. SysRole 저장
            SysRole sysRole = new SysRole();
            sysRole.setName(sysRoleDto.getName());
            sysRole.setCode(sysRoleDto.getCode());
            sysRole.setRemark(sysRoleDto.getRemark());
            sysRole.setStatus(sysRoleDto.getStatus());

            boolean isSuccessInsert = roleMenuService.insertSysRole(sysRole);
            if (isSuccessInsert) {
                return ServerResponse.createBySuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
            }
            return ServerResponse.createByErrorMessage(ResponseCode.INSERT_ERROR.getDesc());
        } else {
            // modify origin
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("roleId", sysRoleDto.getId());
            mapParams.put("roleName", sysRoleDto.getName());
            mapParams.put("roleCode", sysRoleDto.getCode());
            boolean isExistRoleName = roleMenuService.existRoleName(mapParams);
            if (isExistRoleName) {
                return ServerResponse.createByErrorMessage("权限名重复, 请使用其他权限名");
            }

            // 2. SysRole 저장
            SysRole sysRole = new SysRole();
            sysRole.setId(sysRoleDto.getId());
            sysRole.setName(sysRoleDto.getName());
            sysRole.setCode(sysRoleDto.getCode());
            sysRole.setRemark(sysRoleDto.getRemark());
            sysRole.setStatus(sysRoleDto.getStatus());
            boolean isSuccessUpdate = roleMenuService.updateSysRole(sysRole);
            if (isSuccessUpdate) {
                return ServerResponse.createBySuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
            }
            return ServerResponse.createByErrorMessage(ResponseCode.SAVE_ERROR.getDesc());
        }

    }

    /**
     * 권한ID로 해당권한에 부여된 메뉴리스트 가져오기
     * @param roleId
     * @return
     */
    @GetMapping("/rolemenu/detail")
    public ServerResponse<List<SysRoleMenuDto>> getSysRoleMenuByRoleId(@RequestParam("roleId") Integer roleId) {
        if (roleId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        List<SysRoleMenuDto> returnData = roleMenuService.getSysRoleMenuByRoleId(roleId);
        return ServerResponse.createBySuccess(returnData);
    }

    /**
     * 권한ID와 메뉴배열로 권한<->메뉴 관계데이타 편집
     * 1단계: 해당roleId로 데이타 삭제
     * 2단계: roleId, menuIds배열로 데이타 Insert
     * @param roleId
     * @param menuIds
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/rolemenu/modify")
    public ServerResponse modifySysRoleMenu(@RequestParam("roleId") Integer roleId, @RequestParam("menuIds") String menuIds) {
        if (roleId == null || "".equals(menuIds)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            // 1단계
            roleMenuService.deleteSysRoleMenuByRoleId(roleId);
            // 2단계
            List<String> lstMenuIds = Arrays.asList(menuIds.split(","));
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("roleId", roleId);
            mapParams.put("lstMenuIds", lstMenuIds);
            Boolean isSuccessInsert = roleMenuService.insertSysRoleMenuByRoleIdMenuIds(mapParams);
            if (isSuccessInsert) {
                return ServerResponse.createBySuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
            } else {
                return ServerResponse.createByErrorMessage(ResponseCode.SAVE_ERROR.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(ResponseCode.SAVE_ERROR.getDesc());
        }

    }

    /**
     * 권한 리스트가져오기
     * @return
     */
    @GetMapping("/user/list")
    public ServerResponse<List<SysUserDto>> getAllSysUserList() {
        List<SysUserDto> returnData = roleMenuService.getAllSysUserList();
        return ServerResponse.createBySuccess(returnData);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/modify")
    public ServerResponse modifySysUser(@RequestBody SysUserDto sysUserDto) {
        if (StringUtils.isEmpty(sysUserDto.getUsername())
            || StringUtils.isEmpty(sysUserDto.getRealname())
        ) {
            return ServerResponse.createByErrorMessage("用户名和姓名必填");
        }

        if (sysUserDto.getUserSeq() == null || String.valueOf(sysUserDto.getUserSeq()) == "") {
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("username", sysUserDto.getUsername());
            boolean isExistUsername = roleMenuService.existUserName(mapParams);
            if (isExistUsername) {
                return ServerResponse.createByErrorMessage("Username重复,请使用其他用户名");
            }

            SysUser sysUser = new SysUser();
            sysUser.setUsername(sysUserDto.getUsername());
            if (StringUtils.isEmpty(sysUserDto.getPassword())) {
                sysUser.setPassword(new BCryptPasswordEncoder().encode("g12345678"));
            } else {
                sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUserDto.getPassword()));
            }
            sysUser.setRoleNo(sysUserDto.getRoleNo());
            sysUser.setRole(sysUserDto.getRole());
            sysUser.setStatus(sysUserDto.getStatus());
            sysUser.setUserType(sysUserDto.getUserType());
            sysUser.setRealname(sysUserDto.getRealname());
            sysUser.setCompany(sysUserDto.getCompany());
            sysUser.setCompanyType(sysUserDto.getCompanyType());
            sysUser.setMobileNo(sysUserDto.getMobileNo());
            sysUser.setEmail(sysUserDto.getEmail());
            sysUser.setSex(sysUserDto.getSex());
            sysUser.setBirthday(sysUserDto.getBirthday());
            sysUser.setWechat(sysUserDto.getWechat());
            sysUser.setProvinceCode(sysUserDto.getProvinceCode());
            sysUser.setCityCode(sysUserDto.getCityCode());
            sysUser.setDistrictCode(sysUserDto.getDistrictCode());
            sysUser.setAddress(sysUserDto.getAddress());
            sysUser.setQuestion(sysUserDto.getQuestion());
            sysUser.setAnswer(sysUserDto.getAnswer());
            sysUser.setImagePhoto(sysUserDto.getImagePhoto());
            sysUser.setOption01(sysUserDto.getOption01());
            sysUser.setOption02(sysUserDto.getOption02());
            sysUser.setOption03(sysUserDto.getOption03());
            sysUser.setOption04(sysUserDto.getOption04());
            sysUser.setOption05(sysUserDto.getOption05());
            sysUser.setUseYn(sysUserDto.getUseYn());

            boolean isSuccessInsert = roleMenuService.insertSysUser(sysUser);
            if (isSuccessInsert) {
                return ServerResponse.createBySuccessMessage(ResponseCode.INSERT_SUCCESS.getDesc());
            }
            return ServerResponse.createByErrorMessage(ResponseCode.INSERT_ERROR.getDesc());
        } else {
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("username", sysUserDto.getUsername());
            mapParams.put("userSeq", sysUserDto.getUserSeq());
            boolean isExistUsername = roleMenuService.existUserName(mapParams);
            if (isExistUsername) {
                return ServerResponse.createByErrorMessage("Username重复,请使用其他用户名");
            }

            SysUser sysUser = new SysUser();
            sysUser.setUserSeq(sysUserDto.getUserSeq());
            sysUser.setUsername(sysUserDto.getUsername());
//            sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUserDto.getPassword()));
            sysUser.setRoleNo(sysUserDto.getRoleNo());
            sysUser.setRole(sysUserDto.getRole());
            sysUser.setStatus(sysUserDto.getStatus());
            sysUser.setUserType(sysUserDto.getUserType());
            sysUser.setRealname(sysUserDto.getRealname());
            sysUser.setCompany(sysUserDto.getCompany());
            sysUser.setCompanyType(sysUserDto.getCompanyType());
            sysUser.setMobileNo(sysUserDto.getMobileNo());
            sysUser.setEmail(sysUserDto.getEmail());
            sysUser.setSex(sysUserDto.getSex());
            sysUser.setBirthday(sysUserDto.getBirthday());
            sysUser.setWechat(sysUserDto.getWechat());
            sysUser.setProvinceCode(sysUserDto.getProvinceCode());
            sysUser.setCityCode(sysUserDto.getCityCode());
            sysUser.setDistrictCode(sysUserDto.getDistrictCode());
            sysUser.setAddress(sysUserDto.getAddress());
            sysUser.setQuestion(sysUserDto.getQuestion());
            sysUser.setAnswer(sysUserDto.getAnswer());
            sysUser.setImagePhoto(sysUserDto.getImagePhoto());
            sysUser.setOption01(sysUserDto.getOption01());
            sysUser.setOption02(sysUserDto.getOption02());
            sysUser.setOption03(sysUserDto.getOption03());
            sysUser.setOption04(sysUserDto.getOption04());
            sysUser.setOption05(sysUserDto.getOption05());
            sysUser.setUseYn(sysUserDto.getUseYn());

            boolean isSuccessUpdate = roleMenuService.updateSysUser(sysUser);
            if (isSuccessUpdate) {
                return ServerResponse.createBySuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
            }
            return ServerResponse.createByErrorMessage(ResponseCode.SAVE_ERROR.getDesc());
        }

    }

    @GetMapping("/user/role")
    public ServerResponse getRoleListByUserSeq(@RequestParam("userSeq") Integer userSeq) {
        if (userSeq == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        List<SysUserDto> reutrnData = roleMenuService.getRoleListByUserSeq(userSeq);
        return ServerResponse.createBySuccess(reutrnData);
    }

    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping("/user/role/modify")
    public ServerResponse modifySysUserRoleByUserSeq(@RequestParam("userSeq") Integer userSeq, @RequestParam("roleIds") String roleIds) {
        if (userSeq == null || "".equals(roleIds)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            // 1. Delete sys_user_role by userSeq
            roleMenuService.deleteSysUserRoleByUserSeq(userSeq);

            // 2. Insert new data to sys_user_role
            List<String> lstRoleIds = Arrays.asList(roleIds.split(","));
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("userSeq", userSeq);
            mapParams.put("lstRoleIds", lstRoleIds);
            Boolean isSuccessInsert = roleMenuService.insertSysUserRoleByUserSeqRoleIds(mapParams);
            if (isSuccessInsert) {
                return ServerResponse.createBySuccessMessage(ResponseCode.SAVE_SUCCESS.getDesc());
            } else {
                return ServerResponse.createByErrorMessage(ResponseCode.SAVE_ERROR.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(ResponseCode.SAVE_SUCCESS.getDesc());
        }
    }

}
