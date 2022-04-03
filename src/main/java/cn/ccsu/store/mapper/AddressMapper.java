package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.Address;

import java.util.Date;
import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/29 16:03
 * @Version 1.0
 * 收货地址持久层的接口
 */
public interface AddressMapper {

    /**
     * 插入用户的收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id统计收货地址数量
     * @param uid 用户id
     * @return 当前用户收货地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id查询用户的收货地址数据
     * @param uid 用户id
     * @return 收货地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询收货地址数据
     * @param aid 收货地址id
     * @return 收货地址数据，如果没有找到返回null
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的id来修改用户收货地址设置为非默认
     * @param uid 用户id
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     *根据地址id来修改用户收货地址设置为默认
     * @param aid 地址id
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefault(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     *根据收货id删除收货地址数据
     * @param aid 收货地址的id
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid查询当前用户最后一次被修改的uid
     * @param uid 用户id
     * @return 收货地址数据
     */
    Address findLastModified(Integer uid);
}
