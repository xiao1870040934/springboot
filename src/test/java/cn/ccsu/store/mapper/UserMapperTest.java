package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/26 18:12
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserMapperTest {

    @Autowired(required=false)
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user=new User();
        user.setUsername("timi");
        user.setPassword("123");
        Integer res=userMapper.insert(user);
        System.out.println(res);
    }

}
