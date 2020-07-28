package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunMenus;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface YunMenusMapper extends BaseMapper<YunMenus> {
    int deleteByPrimaryKey(Long id);

    int insert(YunMenus record);

    int insertSelective(YunMenus record);

    YunMenus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunMenus record);

    int updateByPrimaryKey(YunMenus record);

    @Select("SELECT ym.menu_code FROM yun_menus ym, yun_role_menus yrm WHERE yrm.permission_id = ym.id and yrm.role_id = #{0}")
    Set<String> getAllMenus(Long rId);

    @Select("SELECT ym.permission_code FROM yun_menus ym, yun_role_menus yrm WHERE yrm.permission_id = ym.id and yrm.role_id = #{0}")
    Set<String> getAllPermission(Long rId);

    @Select("SELECT * FROM yun_menus ym, yun_role_menus yrm WHERE yrm.permission_id = ym.id and yrm.role_id = #{0}")
    List<YunMenus> getMenusListByRoleId(Long rId);
}