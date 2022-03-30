package cn.ccsu.store.service;

import cn.ccsu.store.entity.District;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/30 20:47
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DistrictServiceTest {
    @Autowired
    private IDistrictService districtService;
    @Test
    public void getByParent(){
        List<District> list=districtService.getByParent("86");
        for (District d:list) {
            System.out.println(d);
        }
    }
}
