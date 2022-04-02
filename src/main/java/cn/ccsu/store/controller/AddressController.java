package cn.ccsu.store.controller;

import cn.ccsu.store.entity.Address;
import cn.ccsu.store.service.IAddressService;
import cn.ccsu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/30 18:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{

    @Autowired
    private IAddressService addressService;

    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        addressService.addNewAddress(getUidFromSession(session),getUsernameFromSession(session),address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        List<Address> list=addressService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK,list);
    }

    @RequestMapping("/set_default/{aid}")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session){
        addressService.setDefault(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
