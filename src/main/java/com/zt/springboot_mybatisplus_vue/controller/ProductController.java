package com.zt.springboot_mybatisplus_vue.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.springboot_mybatisplus_vue.pojo.Product;
import com.zt.springboot_mybatisplus_vue.service.ProductService;
import com.zt.springboot_mybatisplus_vue.util.YunResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("yun/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询商品列表和分页
     * @param pageNum
     * @param pageRow
     * @return
     */
    @RequiresPermissions("yun:product:list")
    @RequestMapping("/list")
    public YunResult getList(int pageNum, int pageRow) {
        IPage<Product> page = new Page<>(pageNum, pageRow);
        IPage<Product> page1 = productService.page(page);
        return YunResult.createBySuccess("查询成功", page1);
    }

    /**
     * 添加商品
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("yun:product:add")
    @RequestMapping("/add")
    public YunResult add(@RequestBody JSONObject jsonObject) {
        if(jsonObject != null && !jsonObject.equals("")) {
            //获取商品id
            //获取商品名称
            String productName = jsonObject.getString("productName");
            //商品创建时间和修改时间由数据库的CURRENT_TIMESTAMP特性完成，这里不用操作
            Product product = new Product();
            product.setProductName(productName);
            boolean saveOrUpdate = productService.saveOrUpdate(product);
            return YunResult.createBySuccess("添加成功！", saveOrUpdate);
        }
        return YunResult.createByError();
    }

    /**
     * 更新商品信息
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("yun:product:update")
    @RequestMapping("/update")
    public YunResult update(@RequestBody JSONObject jsonObject) {
        if(jsonObject != null && !jsonObject.equals("")) {
            //获取商品id
            Long id = jsonObject.getLong("id");
            //获取商品名称
            String productName = jsonObject.getString("productName");
            //商品创建时间和修改时间由数据库的CURRENT_TIMESTAMP特性完成，这里不用操作
            Product product = new Product();
            product.setProductName(productName);
            product.setId(id);
            boolean saveOrUpdate = productService.saveOrUpdate(product);
            return YunResult.createBySuccess("更新成功！",saveOrUpdate);
        }
        return YunResult.createByError();
    }

    /**
     * 商品上架下架（逻辑删除，实际是在更新数据库记录信息）
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("yun:product:delete")
    @RequestMapping("/delete")
    public YunResult delete(@RequestBody JSONObject jsonObject) {
        if(jsonObject != null && !jsonObject.equals("")) {
            //获取商品id
            Long id = jsonObject.getLong("id");
            String is_delete = jsonObject.getString("is_delete");//0表示上架，1表示下架
            if(is_delete.equals("0")) {
                is_delete = "1";
            } else {
                is_delete = "0";
            }
            //获取商品名称
            String productName = jsonObject.getString("productName");
            //商品创建时间和修改时间由数据库的CURRENT_TIMESTAMP特性完成，这里不用操作
            Product product = new Product();
            product.setProductName(productName);
            product.setId(id);
            product.setIsDelete(is_delete);
            boolean saveOrUpdate = productService.saveOrUpdate(product);
            return YunResult.createBySuccess("商品上架/下架成功！",saveOrUpdate);
        }
        return YunResult.createByError();
    }
}
