package com.zt.springboot_mybatisplus_vue.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zt.springboot_mybatisplus_vue.pojo.YunRole;

import java.util.Map;

public interface YunRoleService extends IService<YunRole> {
    Map getList();

    int setMenus(JSONObject jsonObject);
}
