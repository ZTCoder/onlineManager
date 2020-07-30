package com.zt.springboot_mybatisplus_vue.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.springboot_mybatisplus_vue.mapper.ChartsMapper;
import com.zt.springboot_mybatisplus_vue.pojo.Charts;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChartsServiceImpl extends ServiceImpl<ChartsMapper, Charts> implements ChartsService {
    @Autowired
    private ChartsMapper chartsMapper;

    @Override
    public Map getList() {
        //女性人员
        List<Charts> wList = chartsMapper.getListBySex("女");
        //男性人员
        List<Charts> mList = chartsMapper.getListBySex("男");

        //女性人员数量
        List<Integer> wListCount = new ArrayList<>();
        //男性人员数量
        List<Integer> mListCount = new ArrayList<>();
        //每月总数量
        List<Integer> sumListCount = new ArrayList<>();
        for(Charts wcharts : wList) {
            for (Charts mcharts : mList) {
                if(wcharts.getTime().equals(mcharts.getTime())) {
                    wListCount.add(wcharts.getCount());
                    mListCount.add(mcharts.getCount());
                    sumListCount.add(wcharts.getCount() + mcharts.getCount());
                }
            }
        }
        Map map = new HashMap();
        map.put("wListCount" ,wListCount);
        map.put("mListCount", mListCount);
        map.put("sumListCount", sumListCount);
        return map;
    }
}
