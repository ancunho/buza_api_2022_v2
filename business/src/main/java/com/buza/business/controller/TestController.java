package com.buza.business.controller;

import com.buza.server.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
