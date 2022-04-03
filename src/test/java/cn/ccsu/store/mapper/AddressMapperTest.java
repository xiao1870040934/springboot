package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/29 17:10
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;
    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(7);
        address.setPhone("15773626715");
        address.setName("女朋友");
        Integer res=addressMapper.insert(address);
        System.out.println(res);
    }
    @Test
    public void countByUid(){
        Integer res=addressMapper.countByUid(7);
        System.out.println(res);
    }
    @Test
    public void findByUid(){
        List<Address> list=addressMapper.findByUid(7);
        for (Address d:list) {
            System.out.println(d);
        }
    }
    @Test
    public void findByAid(){
       Address address=addressMapper.findByAid(6);
        System.out.println(address);
    }
    @Test
    public void updateNonDefault(){
        Integer rows=addressMapper.updateNonDefault(7);
        System.out.println(rows);
    }
    @Test
    public void updateDefault(){
        Integer rows=addressMapper.updateDefault(6,"管理员",new Date());
        System.out.println(rows);
    }
    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(1);
    }
    @Test
    public void findLastModified(){
        Address address=addressMapper.findLastModified(7);
        System.out.println(address);
    }
}
