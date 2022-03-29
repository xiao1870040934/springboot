package cn.ccsu.store.service.impl;

import cn.ccsu.store.entity.Address;
import cn.ccsu.store.mapper.AddressMapper;
import cn.ccsu.store.service.IAddressService;
import cn.ccsu.store.service.ex.AddressCountLimitException;
import cn.ccsu.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/29 19:41
 * @Version 1.0
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count=addressMapper.countByUid(uid);
        if (count>maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        address.setUid(uid);
        Integer isDefault=count==0 ? 1: 0;//1表示默认
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.insert(address);
        if (rows!=1){
            throw new InsertException("插入用户的收货地址产生未知的异常");
        }


    }
}
