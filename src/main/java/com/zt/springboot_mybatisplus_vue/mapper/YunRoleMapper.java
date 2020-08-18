package com.zt.springboot_mybatisplus_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunRole;
import com.zt.springboot_mybatisplus_vue.vo.YunRoleVo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface YunRoleMapper extends BaseMapper<YunRole> {
    int deleteByPrimaryKey(Long id);

    int insert(YunRole record);

    int insertSelective(YunRole record);

    YunRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunRole record);

    int updateByPrimaryKey(YunRole record);

    @Results({
            @Result(property="yunUser",column="roleId",many=@Many(select="com.zt.springboot_mybatisplus_vue.mapper.YunUserMapper.getYunUserByRoleId"))
    })
    @Select("SELECT *, id roleId FROM yun_role yr")
    List<YunRoleVo> getList();
}