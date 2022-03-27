package cn.ccsu.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/27 17:21
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {


    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有则重定向到登录页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url+Controller:映射）
     * @return 如果返回值为true表示放行当前的请求，如果返回值为false则表示拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj=request.getSession().getAttribute("uid");
        if (obj==null){
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
