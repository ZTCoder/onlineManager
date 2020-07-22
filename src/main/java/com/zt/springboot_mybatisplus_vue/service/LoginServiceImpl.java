package com.zt.springboot_mybatisplus_vue.service;

import com.alibaba.fastjson.JSONObject;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class LoginServiceImpl implements LoginService {
    @Override
    public YunResult login(String userName, String passWord) {
        int code = 0;
        JSONObject loginInfo = new JSONObject();
        //1.获取Subject
        Subject currentUser = SecurityUtils.getSubject();
        //2.token登录
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        try {
            currentUser.login(token);
            code = 200;
            loginInfo.put("result", "success");
        } catch (AuthenticationException e) {
            loginInfo.put("result", "fail");
        }
        //获取Session
        Session session = SecurityUtils.getSubject().getSession();
        return YunResult.createByCodeSuccess(code, "执行完毕！b ", loginInfo);
    }

    @Override
    public YunResult getInfo() {
        return null;
    }

    @Override
    public YunResult logOut() {
        return null;
    }
}
