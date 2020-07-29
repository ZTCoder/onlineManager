package com.zt.springboot_mybatisplus_vue.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.springboot_mybatisplus_vue.mapper.YunRoleMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunRole;
import org.springframework.stereotype.Service;

@Service
public class YunRoleServiceImpl extends ServiceImpl<YunRoleMapper, YunRole> implements YunRoleService {
}
