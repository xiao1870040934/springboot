package cn.ccsu.store.controller;

import cn.ccsu.store.service.ex.*;
import cn.ccsu.store.service.impl.UserServiceImpl;
import cn.ccsu.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/27 13:11
 * @Version 1.0
 * 控制层的基类
 */
public class BaseController {
    /*操作成功的状态码*/
    public static  final int OK=200;

    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将这个异常对象传递给此方法的参数列表上
    @ExceptionHandler(ServiceException.class) //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        } else if (e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户数据不存在异常");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户名密码错误的异常");
        }else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }else if (e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新时产生未知异常");
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录用户uid的值
     */
    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取session对象中的username
     * @param session session对象
     * @return 当前登录用户username的值
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}