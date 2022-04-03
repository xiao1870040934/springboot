package cn.ccsu.store.service;


import cn.ccsu.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/29 22:01
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address=new Address();
        address.setPhone("15773625575");
        address.setName("男朋友");
        addressService.addNewAddress(7,"管理员",address);
    }
    @Test
    public void setDefault(){
        addressService.setDefault(6,7,"管理员");
    }
    @Test
    public void delete(){
        addressService.delete(4,7,"管理员");
    }
}
