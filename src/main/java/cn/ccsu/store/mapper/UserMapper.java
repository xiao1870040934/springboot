package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.User;

import java.util.Date;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/26 17:23
 * @Version 1.0
 */
public interface UserMapper {

    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数（增，删，改都会影响行数）
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 如果找到对应的用户的返回改用户，如果没有找到就返回null
     */
    User findByUsername(String username);

    /**
     * 根据用户的uid来修改用户的密码
     * @param uid 用户的id
     * @param password 用户新密码
     * @param modifiedUser 表示修改的执行者
     * @param modifiedTime 表示修改的时间
     * @return 返回受影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的id查询用户的数据
     * @param uid 用户的is
     * @return 如果找到返回对象，反之返回null
     */
    User findByUid(Integer uid);

    /**
     * 更新用户扽数据信息
     * @param user 用户的数据
     * @return 返回值为受影响的行数
     */
    Integer updateInfoByUid(User user);
}
