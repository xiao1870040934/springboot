package cn.ccsu.store.controller;

import cn.ccsu.store.entity.Product;
import cn.ccsu.store.service.IProductService;
import cn.ccsu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/4/4 23:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new JsonResult<>(OK, data);
    }
}
