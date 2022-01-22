package com.buza.business.controller;

import com.buza.server.common.ServerResponse;
import com.buza.server.dto.SysMenuDto;
import com.buza.server.entity.SysMenu;
import com.buza.server.entity.SysRole;
import com.buza.server.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {


    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping("/")
    public String test() {
        return "success";
    }



}
