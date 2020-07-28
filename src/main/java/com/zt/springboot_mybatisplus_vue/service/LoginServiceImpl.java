package com.zt.springboot_mybatisplus_vue.service;

import com.alibaba.fastjson.JSONObject;
import com.zt.springboot_mybatisplus_vue.code.CodeEnum;
import com.zt.springboot_mybatisplus_vue.pojo.YunUser;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import com.zt.springboot_mybatisplus_vue.vo.YunUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private YunMenusService yunMenusService;

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
        return YunResult.createByCodeSuccess(code, "执行完毕！", loginInfo);
    }

    @Override
    public YunResult getInfo() {

        //1.获取当前登录的用户
        Session session = SecurityUtils.getSubject().getSession();
        Subject currentUser = SecurityUtils.getSubject();
        YunUser yunUser = (YunUser) currentUser.getPrincipal();
        YunUserVo yunUserVo = new YunUserVo();
        if(yunUser != null && !yunUser.equals("")) {
            yunUserVo.setId(yunUser.getId());
            yunUserVo.setUserName(yunUser.getUserName());
            yunUserVo.setPassWord(yunUser.getPassWord());
            yunUserVo.setRealName(yunUser.getRealName());
            yunUserVo.setRoleId(yunUser.getRoleId());
            yunUserVo.setCreateTime(yunUser.getCreateTime());
            yunUserVo.setUpdateTime(yunUser.getUpdateTime());
            yunUserVo.setIsDelete(yunUser.getIsDelete());

            //2.获取当前登录用户权限和菜单
            yunUserVo.setMenuList(yunMenusService.getAllMenus(yunUser.getRoleId()));
            yunUserVo.setPermissionList(yunMenusService.getAllPermission(yunUser.getRoleId()));
            return YunResult.createBySuccess("获取信息成功", yunUserVo);
        }
        return YunResult.createByErrorCodeMessage(CodeEnum.ERROR_10001.getErrorCode(), "获取信息失败");
    }

    @Override
    public YunResult logOut() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        }catch (Exception e) {
            return YunResult.createByErrorMessage("退出失败！");
        }
        return YunResult.createByErrorMessage("退出成功！");
    }
}
