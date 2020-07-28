package com.zt.springboot_mybatisplus_vue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zt.springboot_mybatisplus_vue.pojo.YunMenus;

import java.util.List;
import java.util.Set;

public interface YunMenusService extends IService<YunMenus> {

    Set<String> getAllMenus(Long rId);
    Set<String> getAllPermission(Long rId);

    List<YunMenus> getMenusListByRoleId(Long rId);
}
