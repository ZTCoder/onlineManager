package com.zt.springboot_mybatisplus_vue.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.springboot_mybatisplus_vue.mapper.YunUserMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunUser;
import org.springframework.stereotype.Service;

@Service
public class YunUserServiceImpl extends ServiceImpl<YunUserMapper, YunUser> implements YunUserService {

}
