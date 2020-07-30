package com.zt.springboot_mybatisplus_vue.controller;

import com.zt.springboot_mybatisplus_vue.pojo.YunMenus;
import com.zt.springboot_mybatisplus_vue.service.YunMenusService;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("yun/menus")
@RestController
public class MenusController {
    @Autowired
    private YunMenusService yunMenusService;

    /**
     * 父子关系表
     * @return
     */
    @RequiresPermissions("yun:menus:list")
    @RequestMapping("list")
    public YunResult list() {
        Map map = yunMenusService.getMenusList();
        return YunResult.createBySuccess("执行成功", map);
    }

    /**
     * 通过传递对象的方法实现menus添加
     * @param yunMenus
     * @return
     */
    @RequiresPermissions("yun:menus:add")
    @RequestMapping("add")
    public YunResult add(YunMenus yunMenus) {
        if(yunMenus != null && !yunMenus.equals("")) {
            boolean b = yunMenusService.saveOrUpdate(yunMenus);
            return YunResult.createBySuccess("添加成功", b);
        }
        return YunResult.createByError();
    }

    /**
     * 通过传递对象的方式实现menus更新
     * @param yunMenus
     * @return
     */
    @RequiresPermissions("yun:menus:update")
    @RequestMapping("update")
    public YunResult update(YunMenus yunMenus) {
        if(yunMenus != null && !yunMenus.equals("")) {
            boolean b = yunMenusService.saveOrUpdate(yunMenus);
            return YunResult.createBySuccess("更新成功", b);
        }
        return YunResult.createByError();
    }

    /**
     * 删除方法
     * @param ids
     * @return
     */
    @RequiresPermissions("yun:menus:delete")
    @RequestMapping("delete")
    public YunResult delete(String ids) {
        if(ids != null && !ids.equals("")) {
            String[] idsAll = ids.split(",");
            int count = 0;
            boolean flag = false;
            for(String id : idsAll) {
                boolean b = yunMenusService.removeById(id);
                count++;
                flag = true;
            }
            if(flag) {
                return YunResult.createBySuccess("删除成功", count);
            } else {
                return YunResult.createByError();
            }
        }
        return YunResult.createByError();
    }
}
