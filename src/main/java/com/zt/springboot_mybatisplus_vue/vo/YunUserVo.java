package com.zt.springboot_mybatisplus_vue.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zt.springboot_mybatisplus_vue.pojo.YunRole;
import com.zt.springboot_mybatisplus_vue.pojo.YunUser;

import java.util.Set;

public class YunUserVo extends YunUser {
    private Set<String> menuList;
    private Set<String> permissionList;
    @TableField(exist = false)
    private YunRole role;

    public YunRole getRole() {
        return role;
    }

    public void setRole(YunRole role) {
        this.role = role;
    }
    public void setMenuList(Set<String> menuList) {
        this.menuList = menuList;
    }

    public void setPermissionList(Set<String> permissionList) {
        this.permissionList = permissionList;
    }

    public Set<String> getMenuList() {
        return menuList;
    }

    public Set<String> getPermissionList() {
        return permissionList;
    }
}
