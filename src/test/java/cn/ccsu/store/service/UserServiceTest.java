package cn.ccsu.store.service;

import cn.ccsu.store.entity.User;
import cn.ccsu.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/27 12:18
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Autowired
    private IUserService userService;
    @Test
    public void reg(){
        try {
            User user=new User();
            user.setUsername("yuanxin02");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        try {
            User user= userService.login("Tom01","123456");
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void changPassword(){
        try {
            userService.changePassword(7,"管理员","123456","12345");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void getByUid(){
        System.err.println(userService.getByUid(1));
    }
    @Test
    public void changeInfo(){
        User user=new User();
        user.setPhone("123456789");
        user.setEmail("ccsu@cs.com");
        user.setGender(0);
        userService.changeInfo(1,"管理员",user);
    }
    @Test
    public void changeAvatar(){
        userService.changeAvatar(1,"/js.png","timi");
    }
}
