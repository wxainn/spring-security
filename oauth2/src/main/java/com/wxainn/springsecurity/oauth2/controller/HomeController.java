package com.wxainn.springsecurity.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author 王晓安
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String index() {
        return "THis is Home Page!";
    }
}
