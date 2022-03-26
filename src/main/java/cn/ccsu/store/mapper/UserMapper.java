package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.User;

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
}
