//package com.buza.system.controller;
//
//import com.buza.server.common.ServerResponse;
//import com.buza.server.entity.SysMenu;
//import com.buza.server.entity.SysRole;
//import com.buza.server.service.RoleMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.Map;
//
//@RefreshScope
//@RestController
//@RequestMapping(value = "/test")
//public class TestController {
//
//
//    @Autowired
//    private RoleMenuService roleMenuService;
//
//    @RequestMapping("/")
//    public String test() {
//        return "success";
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String index() {
//        return "Hello world";
//    }
//
////    @RequestMapping(value = "/users", method = RequestMethod.GET)
////    public ResponseEntity<List<Map<String, Object>>> getAllSysUser(HttpServletRequest request, HttpServletResponse response) {
////        List<Map<String, Object>> getAllSysUsers = helloService.getAllSysUser();
////
////        return ResponseEntity.ok().body(getAllSysUsers);
////    }
//
//
//}
