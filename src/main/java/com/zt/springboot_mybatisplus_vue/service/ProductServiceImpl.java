package com.zt.springboot_mybatisplus_vue.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.springboot_mybatisplus_vue.mapper.ProductMapper;
import com.zt.springboot_mybatisplus_vue.pojo.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
