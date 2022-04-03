package cn.ccsu.store.service;

import cn.ccsu.store.entity.Address;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/29 19:19
 * @Version 1.0
 */
public interface IAddressService {

    /**
     * 增加用户地址
     * @param uid 用户id
     * @param username 用户名
     * @param address 用户地址数据
     */
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    /**
     * 修改某个用户某条收货地址数据为默认地址
     * @param aid 地址id
     * @param uid 用户id
     * @param username 表示修改执行的人
     */
    void setDefault(Integer aid,Integer uid,String username);

    /**
     * 删除用户选中的收货地址数据
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void delete(Integer aid,Integer uid,String username);
}
