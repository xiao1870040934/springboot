package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.Cart;
import cn.ccsu.store.vo.CartVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/4/6 15:02
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CartMapperTest {
    @Autowired
    CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(1);
        cart.setPid(2);
        cart.setNum(3);
        cart.setPrice(4L);
        Integer rows = cartMapper.insert(cart);
        System.out.println("rows=" + rows);
    }
    @Test
    public void updateNumByCid() {
        Integer cid = 1;
        Integer num = 10;
        String modifiedUser = "购物车管理员";
        Date modifiedTime = new Date();
        Integer rows = cartMapper.updateNumByCid(cid, num, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByUidAndPid() {
        Integer uid = 1;
        Integer pid = 2;
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        System.out.println(result);
    }
    @Test
    public void findVOByUid() {
        List<CartVO> list = cartMapper.findVOByUid(31);
        System.out.println(list);
    }
    @Test
    public void findByCid() {
        Integer cid = 3;
        Cart result = cartMapper.findByCid(cid);
        System.out.println(result);
    }
    @Test
    public void findVOByCids() {
        Integer[] cids = {1, 2, 6, 7, 8, 9, 10};
        List<CartVO> list = cartMapper.findVOByCids(cids);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }
}
