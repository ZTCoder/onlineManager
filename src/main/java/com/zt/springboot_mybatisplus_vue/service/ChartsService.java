package com.zt.springboot_mybatisplus_vue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zt.springboot_mybatisplus_vue.pojo.Charts;

import java.util.Map;

public interface ChartsService extends IService<Charts> {
    Map getList();
}
