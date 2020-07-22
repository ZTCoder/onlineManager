package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunUser;

public interface YunUserMapper extends BaseMapper<YunUser> {
    int deleteByPrimaryKey(Long id);

    int insert(YunUser record);

    int insertSelective(YunUser record);

    YunUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunUser record);

    int updateByPrimaryKey(YunUser record);
}