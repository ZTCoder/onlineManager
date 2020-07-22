package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunRole;

public interface YunRoleMapper extends BaseMapper<YunRole> {
    int deleteByPrimaryKey(Long id);

    int insert(YunRole record);

    int insertSelective(YunRole record);

    YunRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunRole record);

    int updateByPrimaryKey(YunRole record);
}