package cn.ccsu.store.service;

import cn.ccsu.store.entity.Product;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/4/4 23:22
 * @Version 1.0
 */
public interface IProductService {

    List<Product> findHotList();
}
