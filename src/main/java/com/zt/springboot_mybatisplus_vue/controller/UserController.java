package com.zt.springboot_mybatisplus_vue.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.springboot_mybatisplus_vue.pojo.YunRole;
import com.zt.springboot_mybatisplus_vue.pojo.YunUser;
import com.zt.springboot_mybatisplus_vue.service.YunRoleService;
import com.zt.springboot_mybatisplus_vue.service.YunUserService;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("yun/user")
@RestController
public class UserController {
    @Autowired
    private YunUserService yunUserService;
    @Autowired
    private YunRoleService yunRoleService;

    /**
     * 查询用户和用户所在的角色
     * @param pageNum
     * @param pageRow
     * @return
     */
    @RequiresPermissions("yun:user:list")
    @RequestMapping("list")
    public YunResult getList(int pageNum, int pageRow) {
        IPage<YunUser> iPage = new Page<>(pageNum, pageRow);
        //只查询没有冻结的用户即is_delete=0
        IPage<YunUser> page = yunUserService.page(iPage, new QueryWrapper<YunUser>().lambda().eq(YunUser::getIsDelete, "0"));
        List list = new ArrayList<>();
        for(YunUser yunUser : page.getRecords()) {
            YunRole yunRole = yunRoleService.getById(yunUser.getRoleId());
            yunUser.setYunRole(yunRole);
            list.add(yunUser);
        }
        page.setRecords(list);
        return YunResult.createBySuccess("查询成功！", page);
    }

    /**
     * 添加新用户
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("yun:user:add")
    @RequestMapping("add")
    public YunResult add(@RequestBody JSONObject jsonObject) {
        if(jsonObject != null && !jsonObject.equals("")) {
            String userName = jsonObject.getString("userName");
            String passWord = jsonObject.getString("passWord");
            String realName = jsonObject.getString("realName");
            Long rId = jsonObject.getLong("roleId");

            YunUser yunUser = new YunUser();
            yunUser.setUserName(userName);
            yunUser.setPassWord(passWord);
            yunUser.setRealName(realName);
            yunUser.setRoleId(rId);
            yunUser.setIsDelete("0");
            boolean b = yunUserService.saveOrUpdate(yunUser);
            return YunResult.createBySuccess("添加用户成功！", b);
        }
        return YunResult.createByError();
    }

    /**
     * 更新用户信息
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("yun:user:update")
    @RequestMapping("update")
    public YunResult update(@RequestBody JSONObject jsonObject) {
        if(jsonObject != null && !jsonObject.equals("")) {
            String userName = jsonObject.getString("userName");
            String passWord = jsonObject.getString("passWord");
            String realName = jsonObject.getString("realName");
            Long rId = jsonObject.getLong("roleId");
            Long id = jsonObject.getLong("id");

            YunUser yunUser = new YunUser();
            yunUser.setUserName(userName);
            yunUser.setPassWord(passWord);
            yunUser.setRealName(realName);
            yunUser.setRoleId(rId);
            yunUser.setIsDelete("0");
            yunUser.setId(id);
            boolean b = yunUserService.saveOrUpdate(yunUser);
            return YunResult.createBySuccess("更新用户信息成功！", b);
        }
        return YunResult.createByError();
    }

    /**
     * 冻结解冻用户（即逻辑删除用户）
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("yun:user:delete")
    @RequestMapping("delete")
    public YunResult delete(@RequestBody JSONObject jsonObject) {
        if(jsonObject != null && !jsonObject.equals("")) {
            String is_delete = jsonObject.getString("is_delete");
            Long id = jsonObject.getLong("id");
            YunUser yunUser = new YunUser();
            yunUser.setId(id);
            yunUser.setIsDelete(is_delete);
            boolean b = yunUserService.saveOrUpdate(yunUser);
            return YunResult.createBySuccess("冻结/解冻用户成功", b);
        }
        return YunResult.createByError();
    }
}
