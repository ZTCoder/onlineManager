package com.zt.springboot_mybatisplus_vue.controller;

import com.alibaba.fastjson.JSONObject;
import com.zt.springboot_mybatisplus_vue.pojo.YunRole;
import com.zt.springboot_mybatisplus_vue.service.YunMenusService;
import com.zt.springboot_mybatisplus_vue.service.YunRoleService;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import com.zt.springboot_mybatisplus_vue.vo.YunMenusVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("yun/role")
@RestController
public class RoleController {
    @Autowired
    private YunRoleService yunRoleService;
    @Autowired
    private YunMenusService yunMenusService;

    /**
     * 查询带有菜单的角色列表
     * @return
     */
    @RequiresPermissions("yun:role:list")
    @RequestMapping("list")
    public YunResult getList() {
        Map map = yunRoleService.getList();
        return YunResult.createBySuccess("查询成功", map);
    }

    /**
     * 查询权限的列表
     * @return
     */
    @RequiresPermissions("yun:role:list")
    @RequestMapping("getAlllist")
    public YunResult getAllList() {
        List<YunMenusVo> list = yunMenusService.getAllList();
        return YunResult.createBySuccess("查询成功", list);
    }

    /**
     * role添加方法
     * @param jsonObject
     * @return
     */

    /**
     * role更新方法
     * @param yunRole
     * @return
     */
    @RequiresPermissions( value = {"yun:role:add","yun:role:update"},logical = Logical.OR)
    @RequestMapping("add")
    public YunResult add(@RequestBody JSONObject yunRole){
        if(yunRole!=null&&!yunRole.equals("")) {
            String roleName = yunRole.getString("roleName");
            Long id = yunRole.getLong("id");
            //is_delete通常前端会把0和1变成true 和 flase
            String is_delete = yunRole.getString("isDelete");// "true","false"
            YunRole yunRole1 = new YunRole();
            yunRole1.setId(id);
            yunRole1.setRoleName(roleName);
            if(is_delete!=null&&!is_delete.equals("")){
                if(is_delete.equals("true")) {
                    yunRole1.setIsDelete("0");//表示逻辑未删除
                } else {
                    yunRole1.setIsDelete("1");//表示逻辑删除
                }
            }
            boolean saveOrUpdate = yunRoleService.saveOrUpdate(yunRole1);
            return  YunResult.createBySuccess("执行成功！",saveOrUpdate);

        }

        return YunResult.createByError();
    }

    @RequiresPermissions("yun:role:update")
    @RequestMapping("update")
    public YunResult update(@RequestBody JSONObject jsonObject) {
        if (jsonObject != null && !jsonObject.equals("")) {
            String roleName = jsonObject.getString("roleName");
            Long id = jsonObject.getLong("id");
            String is_delete = jsonObject.getString("isDelete");
            YunRole yunRole = new YunRole();
            yunRole.setRoleName(roleName);
            yunRole.setId(id);
            if(is_delete != null && !is_delete.equals("")) {
                if(is_delete.equals("true")) {
                    yunRole.setIsDelete("0");
                }else{
                    yunRole.setIsDelete("1");
                }
            }
            boolean update = yunRoleService.saveOrUpdate(yunRole);
            return YunResult.createBySuccess("更新成功", update);
        }
        return YunResult.createByError();
    }

    /**
     * 从数据库删除信息记录，不同于逻辑删除，是物理删除
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("yun:role:delete")
    @RequestMapping("delete")
    public YunResult delete(@RequestBody JSONObject jsonObject) {
        if(jsonObject != null && !jsonObject.equals("")) {
            Long id = jsonObject.getLong("yunRole");
            boolean removeById = yunRoleService.removeById(id);
            return YunResult.createBySuccess("删除成功", removeById);
        }
        return YunResult.createByError();
    }

    /**
     * 分配菜单权限
     * @param jsonObject
     * @return
     */
    @RequiresPermissions(value = {"yun:role:add", "yun:role:update"}, logical = Logical.OR)
    @RequestMapping("setMenus")
    public YunResult setMenus(@RequestBody JSONObject jsonObject) {
        if(jsonObject != null && !jsonObject.equals("")) {
            int i = yunRoleService.setMenus(jsonObject);
            if(i > 0) {
                return YunResult.createBySuccess("执行成功", i);
            }
        }
        return YunResult.createByError();
    }
}
