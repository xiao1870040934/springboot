package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.District;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/30 19:57
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DistrictMapperTest {
    @Autowired
    private DistrictMapper districtMapper;
    @Test
    public void findByParent(){
        List<District> list=districtMapper.findByParent("210100");
        for (District t:list) {
            System.out.println(t);
        }
    }
    @Test
    public void findNameByCode(){
        System.out.println(districtMapper.findNameByCode("610000"));
    }
}
