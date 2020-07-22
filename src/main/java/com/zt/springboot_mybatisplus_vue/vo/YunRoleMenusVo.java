package com.zt.springboot_mybatisplus_vue.vo;

import com.zt.springboot_mybatisplus_vue.pojo.YunRoleMenus;

import java.util.List;

public class YunRoleMenusVo  extends YunRoleMenus {

    private List<YunMenusVo> yunMenus;

    private List<String> children;

    public List<YunMenusVo> getYunMenus() {
        return yunMenus;
    }

    public void setYunMenus(List<YunMenusVo> yunMenus) {
        this.yunMenus = yunMenus;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }
}
