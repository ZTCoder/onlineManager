package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunRoleMenus;

public interface YunRoleMenusMapper extends BaseMapper<YunRoleMenus> {
    int deleteByPrimaryKey(Long id);

    int insert(YunRoleMenus record);

    int insertSelective(YunRoleMenus record);

    YunRoleMenus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunRoleMenus record);

    int updateByPrimaryKey(YunRoleMenus record);
}