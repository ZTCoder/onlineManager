package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.Charts;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChartsMapper extends BaseMapper<Charts> {
    int deleteByPrimaryKey(Long id);

    int insert(Charts record);

    int insertSelective(Charts record);

    Charts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Charts record);

    int updateByPrimaryKey(Charts record);

    @Select("SELECT * FROM t_charts tc WHERE tc.sex = #{0}")
    List<Charts> getListBySex(String sex);
}