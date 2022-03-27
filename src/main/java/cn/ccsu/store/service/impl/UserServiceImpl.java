package cn.ccsu.store.service.impl;

import cn.ccsu.store.entity.User;
import cn.ccsu.store.mapper.UserMapper;
import cn.ccsu.store.service.IUserService;
import cn.ccsu.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.UUID;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/27 12:07
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String userName=user.getUsername();
        User result=userMapper.findByUsername(userName);
        if (result!=null){
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密处理的实现：md5算法的形式
        //（串+密码+串） ---MD5算法加密，连续加密三次
        //盐值就是一个随机的字符串
        String oldPassword=user.getPassword();
        String salt= UUID.randomUUID().toString().toUpperCase();
        String md5Password=getMD5Password(oldPassword,salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedUser(userName);
        user.setModifiedUser(userName);
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());
        Integer rows=userMapper.insert(user);
        if (rows!=1){
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result=userMapper.findByUsername(username);
        if (result==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldPassword=result.getPassword();
        String newMD5Password=getMD5Password(password,result.getSalt());
        if (!newMD5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        if (result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        User user=new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result=userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldMd5=getMD5Password(oldPassword,result.getSalt());
        if (!result.getPassword().equals(oldMd5)){
            throw new PasswordNotMatchException("密码错误");
        }
        String newMd5 = getMD5Password(newPassword, result.getSalt());
        userMapper.updatePasswordByUid(uid,newMd5,username,new Date());

    }

    @Override
    public User getByUid(Integer uid) {
        User result=userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        User user=new User();
        user.setGender(result.getGender());
        user.setEmail(result.getEmail());
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result=userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        //user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows=userMapper.updateInfoByUid(user);
        if (rows!=1){
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    /*定义一个MD5加密算法*/
    private String getMD5Password(String password,String salt){
        for (int i=0;i<3;i++){
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
