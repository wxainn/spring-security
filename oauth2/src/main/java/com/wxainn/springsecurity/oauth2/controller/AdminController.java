package com.wxainn.springsecurity.oauth2.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员页面
 *
 * @author 王晓安
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(value = "/current")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
