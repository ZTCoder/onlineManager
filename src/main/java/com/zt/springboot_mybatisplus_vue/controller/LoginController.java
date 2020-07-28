package com.zt.springboot_mybatisplus_vue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zt.springboot_mybatisplus_vue.service.LoginService;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("yun/login")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录方式1，String字符串传递
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping("/auth1")
    public YunResult login(String userName, String passWord) {
        YunResult result = loginService.login(userName, passWord);
        return result;
    }

    /**
     * 登录方式2，JSONbject字符串的方式登录
     * @param jsonObject
     * @return
     */
    @RequestMapping("/auth")
    public YunResult loginVue(@RequestBody JSONObject jsonObject) {
        if(jsonObject != null && !jsonObject.equals("")) {
            String userName = jsonObject.getString("userName");
            String passWord = jsonObject.getString("passWord");
            YunResult result = loginService.login(userName, passWord);
            return result;
        }
        return YunResult.createByError();
    }
}
