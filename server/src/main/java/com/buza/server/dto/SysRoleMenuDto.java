package com.buza.server.dto;

import com.buza.server.entity.SysMenu;
import com.buza.server.entity.SysRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class SysRoleMenuDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long menuId;
    private Long menuParentId;
    private String menuName;
    private String menuPath;
    private String menuComponent;
    private String menuOrderNum;
    private String menuStatus;

    private Long roleId;
    private String roleName;
    private String roleCode;
    private String roleRemark;
    private String roleStatus;

    private String isContain;


}
