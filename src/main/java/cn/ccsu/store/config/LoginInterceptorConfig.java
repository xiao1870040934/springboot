package cn.ccsu.store.config;

import cn.ccsu.store.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/27 18:26
 * @Version 1.0
 * 处理拦截器的注册
 */
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Value("${uploadInfo.upload}")
    private String uploadPattern;
    @Value("${uploadInfo.location}")
    private String uploadLocation;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截器
        HandlerInterceptor interceptor=new LoginInterceptor();
        //配置白名单
        List<String> list=new ArrayList<>();
        list.add("/bootstrap3/**");
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/upload/**");
        list.add("/web/register.html");
        list.add("/web/login.html");
        list.add("/web/index.html");
        list.add("/web/product.html");
        list.add("/user/reg");
        list.add("/user/login");
        //完成拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(list);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadPattern)
                .addResourceLocations("file:" + uploadLocation);
    }
}
