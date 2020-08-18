package com.zt.springboot_mybatisplus_vue.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.springboot_mybatisplus_vue.mapper.YunMenusMapper;
import com.zt.springboot_mybatisplus_vue.mapper.YunRoleMapper;
import com.zt.springboot_mybatisplus_vue.mapper.YunRoleMenusMapper;
import com.zt.springboot_mybatisplus_vue.pojo.YunMenus;
import com.zt.springboot_mybatisplus_vue.pojo.YunRole;
import com.zt.springboot_mybatisplus_vue.pojo.YunRoleMenus;
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

    /**
     * 权限分配
     * @param jsonObject
     * @return
     */
    @Override
    public int setMenus(JSONObject jsonObject) {
        int count = 0;
        //vue的tree的特性["100", "101" ]
        String ids = jsonObject.getString("ids");
        Long roleId = jsonObject.getLong("roleId");
        String[] checkIds = ids.substring(1, ids.length()-1).split(",");
        //为了保证选中的一致性，可以先删除再分配
        yunRoleMenusMapper.deleteByRoleId(roleId);
        for(String id : checkIds) {
            if (id != null && !id.equals("")) {
                Long checkId = Long.valueOf(id.trim()).longValue();
                YunRoleMenus yunRoleMenus = new YunRoleMenus();
                yunRoleMenus.setRoleId(roleId);
                yunRoleMenus.setPermissionId(checkId);
                int insert = yunRoleMenusMapper.insert(yunRoleMenus);
                count = count + insert;
            }
        }
        return count;
    }
}
