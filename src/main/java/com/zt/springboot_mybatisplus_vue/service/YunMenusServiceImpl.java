package com.zt.springboot_mybatisplus_vue.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.springboot_mybatisplus_vue.mapper.YunMenusMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunMenus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class YunMenusServiceImpl extends ServiceImpl<YunMenusMapper, YunMenus> implements YunMenusService{

    @Autowired
    private YunMenusMapper yunMenusMapper;
    /**
     * 根据权限id获取用户所有的菜单
     * @param rId
     * @return
     */
    @Override
    public Set<String> getAllMenus(Long rId) {
        return yunMenusMapper.getAllMenus(rId);
    }

    /**
     * 根据权限id获取用户所有的权限
     * @param rId
     * @return
     */
    @Override
    public Set<String> getAllPermission(Long rId) {
        return yunMenusMapper.getAllPermission(rId);
    }

    @Override
    public List<YunMenus> getMenusListByRoleId(Long rId) {
        return yunMenusMapper.getMenusListByRoleId(rId);
    }
}
