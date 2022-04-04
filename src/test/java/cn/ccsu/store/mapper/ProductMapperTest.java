package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/4/4 23:11
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotList(){
        List<Product> list=productMapper.findHotList();
        for (Product t:list ) {
            System.out.println(t);
        }
    }
}
