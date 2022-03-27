package cn.ccsu.store.controller;

import cn.ccsu.store.entity.User;
import cn.ccsu.store.service.IUserService;
import cn.ccsu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/27 12:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user){
       userService.reg(user);
       return new JsonResult<>(OK);
    }

    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data=userService.login(username,password);
        //在session对象中完成数据绑定（session是全局的）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);

    }
}
