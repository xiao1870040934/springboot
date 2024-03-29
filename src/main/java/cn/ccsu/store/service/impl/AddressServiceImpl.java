package cn.ccsu.store.service.impl;

import cn.ccsu.store.entity.Address;
import cn.ccsu.store.mapper.AddressMapper;
import cn.ccsu.store.service.IAddressService;
import cn.ccsu.store.service.IDistrictService;
import cn.ccsu.store.service.ex.*;
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
            //t.setAid(null);
            //t.setUid(null);
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

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address address= addressMapper.findByAid(aid);
        if (address==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows=addressMapper.updateNonDefault(uid);
        if (rows<1){
            throw new UpdateException("更新数据产生未知的异常");
        }
        rows=addressMapper.updateDefault(aid,username,new Date());
        if (rows!=1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result=addressMapper.findByAid(aid);
        if (result==null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows=addressMapper.deleteByAid(aid);
        if (rows!=1){
            throw new DeleteException("删除数据产生未知的异常");
        }
        Integer count=addressMapper.countByUid(uid);
        if (count==0){
            return;
        }
        if (result.getIsDefault()==0){
            return;
        }
        Address address=addressMapper.findLastModified(uid);
        rows=addressMapper.updateDefault(address.getAid(),username,new Date());
        if (rows!=1){
            throw new UpdateException("更新数据时产生未知的异常");
        }

    }
}
