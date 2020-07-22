package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.Charts;

public interface ChartsMapper extends BaseMapper<Charts> {
    int deleteByPrimaryKey(Long id);

    int insert(Charts record);

    int insertSelective(Charts record);

    Charts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Charts record);

    int updateByPrimaryKey(Charts record);
}