package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.Product;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/4/4 23:00
 * @Version 1.0
 */
public interface ProductMapper {

    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);
}
