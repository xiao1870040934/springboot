package cn.ccsu.store.service.impl;

import cn.ccsu.store.entity.Product;
import cn.ccsu.store.mapper.ProductMapper;
import cn.ccsu.store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/4/4 23:23
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }
}
