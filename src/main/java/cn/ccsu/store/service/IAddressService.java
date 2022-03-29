package cn.ccsu.store.service;

import cn.ccsu.store.entity.Address;

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
}
