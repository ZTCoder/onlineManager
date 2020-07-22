package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunMenus;

public interface YunMenusMapper extends BaseMapper<YunMenus> {
    int deleteByPrimaryKey(Long id);

    int insert(YunMenus record);

    int insertSelective(YunMenus record);

    YunMenus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunMenus record);

    int updateByPrimaryKey(YunMenus record);
}