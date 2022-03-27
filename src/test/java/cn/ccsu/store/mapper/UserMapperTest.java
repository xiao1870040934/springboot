package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

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
    @Test
    public void findByUsername(){
        User user=userMapper.findByUsername("timi");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        Integer res=userMapper.updatePasswordByUid(1,"12346","管理员",new Date());
        System.out.println(res);
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(1));
    }

    @Test
    public void updateInfoByUid(){
        User user=new User();
        user.setUid(1);
        user.setPhone("13762635575");
        user.setEmail("timi@02qq.com");
        user.setGender(1);
        Integer res=userMapper.updateInfoByUid(user);
        System.out.println(res);

    }
}
