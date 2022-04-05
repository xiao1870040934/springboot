package cn.ccsu.store.service;

import cn.ccsu.store.entity.Product;
import cn.ccsu.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author 潇洒哥queen
 * @Date 2022/4/5 12:23
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    @Autowired
    private IProductService productService;
    @Test
    public void findById() {
        try {
            Integer id = 100000179;
            Product result = productService.findById(id);
            System.out.println(result);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
