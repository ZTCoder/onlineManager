package com.zt.springboot_mybatisplus_vue.controller;

import com.zt.springboot_mybatisplus_vue.service.YunMenusService;
import com.zt.springboot_mybatisplus_vue.service.YunRoleService;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("yun/role")
@RestController
public class RoleController {
    @Autowired
    private YunRoleService yunRoleService;
    @Autowired
    private YunMenusService yunMenusService;

    @RequiresPermissions("yun:role:list")
    @RequestMapping("list")
    public YunResult getList(int pageNum, int pageRow) {
        Map map = yunRoleService.getList();
        return YunResult.createBySuccess("查询成功", map);
    }
}
