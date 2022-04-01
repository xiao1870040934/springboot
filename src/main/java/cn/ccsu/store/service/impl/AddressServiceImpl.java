package cn.ccsu.store.service.impl;

import cn.ccsu.store.entity.Address;
import cn.ccsu.store.mapper.AddressMapper;
import cn.ccsu.store.service.IAddressService;
import cn.ccsu.store.service.IDistrictService;
import cn.ccsu.store.service.ex.AddressCountLimitException;
import cn.ccsu.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/29 19:41
 * @Version 1.0
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;
    //在添加用户的收货地址的业务层依赖与IDistrictService的业务接口
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count=addressMapper.countByUid(uid);
        if (count>maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        String provinceName=districtService.getNameByCode(address.getProvinceCode());
        String cityName=districtService.getNameByCode(address.getCityCode());
        String areaName=districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);
        Integer isDefault=count==0 ? 1: 0;//1表示默认
        address.setIsDefault(isDefault);
        //补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.insert(address);
        if (rows!=1){
            throw new InsertException("插入用户的收货地址产生未知的异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list=addressMapper.findByUid(uid);
        for (Address t:list ) {
            t.setAid(null);
            t.setUid(null);
            t.setProvinceCode(null);
            t.setCityCode(null);
            t.setAreaCode(null);
            t.setTel(null);
            t.setIsDefault(null);
            t.setCreatedTime(null);
            t.setCreatedUser(null);
            t.setModifiedTime(null);
            t.setModifiedUser(null);

        }
        return list;
    }


}
