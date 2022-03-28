package cn.ccsu.store.service;

import cn.ccsu.store.entity.User;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/27 12:04
 * @Version 1.0
 * 用户模块业务层接口
 */

public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     *用户登录功能
     * @param username 用户名
     * @param password 密码
     * @return 当前匹配的数据，没有就返回null
     */
    User login(String username,String password);

    /**
     * 用户修改密码功能
     * @param uid 用户id
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    /**
     * 根据用户id查询数据
     * @param uid 用户bid
     * @return 用户的数据
     */
    User getByUid(Integer uid);

    /**
     * 更新用户的数据操作
     * @param uid 用户id
     * @param username 用户名
     * @param user 用户对象数据
     */
    void changeInfo(Integer uid,String username,User user);

    /**
     * 修改用户的头像
     * @param uid 用户id
     * @param avatar 用户头像路径
     * @param username 用户名
     */
    void changeAvatar(Integer uid,String avatar,String username);
}
