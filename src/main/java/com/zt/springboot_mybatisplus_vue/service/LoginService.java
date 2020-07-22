package com.zt.springboot_mybatisplus_vue.service;

import com.zt.springboot_mybatisplus_vue.util.YunResult;

public interface LoginService {
    /**
     * 登录验证
     */
    YunResult login(String userName, String passWord);

    /**
     * 获取登录者信息
     */
    YunResult getInfo();

    /**
     * 退出登录
     */
    YunResult logOut();
}
