package com.zt.springboot_mybatisplus_vue.controller;

import com.zt.springboot_mybatisplus_vue.service.ChartsService;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("yun/charts")
@RestController
public class ChartsController {
    @Autowired
    private ChartsService chartsService;
    /**
     * 图形列表的输出
     * 网站下单量，每月男性的数量、女性的数量，每个月男女总人数
     */

    /**
     * 查询图表数据
     * @return
     */
    @RequiresPermissions("yun:charts:list")
    @RequestMapping("list")
    public YunResult getCharts() {
        Map map = chartsService.getList();
        return YunResult.createBySuccess("查询图表数据成功", map);
    }
}
