package cn.ccsu.store.service;

import cn.ccsu.store.service.ex.ServiceException;
import cn.ccsu.store.vo.CartVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/4/6 15:38
 * @Version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CartServiceTest {
    @Autowired
    private ICartService cartService;
    @Test
    public void addToCart() {
        try {
            Integer uid = 7;
            Integer pid = 10000007;
            Integer amount = 1;
            String username = "Tom";
            cartService.addToCart(uid, pid, amount, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void getVOByUid() {
        List<CartVO> list = cartService.getVOByUid(31);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }
    @Test
    public void addNum() {
        try {
            Integer cid = 3;
            Integer uid = 7;
            String username = "管理员";
            Integer num = cartService.addNum(cid, uid, username);
            System.out.println("OK. New num=" + num);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
