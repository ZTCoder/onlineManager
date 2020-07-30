package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunMenus;
import com.zt.springboot_mybatisplus_vue.vo.YunMenusVo;
import org.apache.ibatis.annotations.Param;
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

    @Select("SELECT * FROM yun_menus ym WHERE ym.parent_id = #{0}")
    List<YunMenusVo> getMenusListByPid(Long pId);

    @Select("SELECT ym.id, ym.menu_name, ym.parent_id, ym.menu_code, ym.permission_name FROM yun_menus ym, yun_role_menus yrm " +
            "WHERE ym.id = yrm.permission_id and ym.parent_id = #{pId} and yrm.role_id = #{rId} ORDER BY ym.id")
    List<YunMenus> getPerMisssion(@Param("pId") Long pId, @Param("rId") Long rId);

    @Select("SELECT * FROM yun_menus ym WHERE ym.parent_id = #{0}")
    List<YunMenus> getPermissionList(Long id);

    @Select("SELECT * FROM yun_menus ym")
    List<YunMenusVo> getList();
}