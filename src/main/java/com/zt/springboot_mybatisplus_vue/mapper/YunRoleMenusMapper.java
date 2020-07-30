package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunRoleMenus;
import org.apache.ibatis.annotations.Select;

public interface YunRoleMenusMapper extends BaseMapper<YunRoleMenus> {
    int deleteByPrimaryKey(Long id);

    int insert(YunRoleMenus record);

    int insertSelective(YunRoleMenus record);

    YunRoleMenus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunRoleMenus record);

    int updateByPrimaryKey(YunRoleMenus record);

    @Select("DELETE * FROM yun_role_menus WHERE role_id = #{0}")
    void deleteByRoleId(Long roleId);
}