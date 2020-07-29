package com.zt.springboot_mybatisplus_vue.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.springboot_mybatisplus_vue.mapper.YunMenusMapper;
import com.zt.springboot_mybatisplus_vue.mapper.YunRoleMapper;
import com.zt.springboot_mybatisplus_vue.mapper.YunRoleMenusMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunMenus;
import com.zt.springboot_mybatisplus_vue.pojo.YunRole;
import com.zt.springboot_mybatisplus_vue.vo.YunMenusVo;
import com.zt.springboot_mybatisplus_vue.vo.YunRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YunRoleServiceImpl extends ServiceImpl<YunRoleMapper, YunRole> implements YunRoleService {
    @Autowired
    private YunRoleMapper yunRoleMapper;
    @Autowired
    private YunMenusMapper yunMenusMapper;
    @Autowired
    private YunRoleMenusMapper yunRoleMenusMapper;

    @Override
    public Map getList() {
        List<YunRoleVo> yunRoleVoList = yunRoleMapper.getList();
        for(YunRoleVo yunRoleVo: yunRoleVoList) {
            //获取菜单根节点
            List<YunMenusVo> menusList = yunMenusMapper.getMenusListByPid(0L);

            List<Long> checkIdsList = new ArrayList<>();
            List<YunMenusVo> menusVoList = new ArrayList<>();
            for(YunMenusVo yunMenusVo: menusList) {
                //获取权限 前端复选框回显做准备
                List<YunMenus> permissionList = yunMenusMapper.getPerMisssion(yunMenusVo.getId(), yunRoleVo.getId());
                if(permissionList != null && !permissionList.equals("")) {
                    for(YunMenus yunMenus : permissionList) {
                        checkIdsList.add(yunMenus.getId());
                    }
                }
                yunMenusVo.setPermissionList(permissionList);
                menusVoList.add(yunMenusVo);
            }
            yunRoleVo.setCheckedIds(checkIdsList);
            yunRoleVo.setYunMenusVo(menusVoList);
        }
        Map map = new HashMap();
        map.put("list", yunRoleVoList);
        return map;
    }
}
