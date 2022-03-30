package cn.ccsu.store.controller;

import cn.ccsu.store.entity.District;
import cn.ccsu.store.service.IDistrictService;
import cn.ccsu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/30 20:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> list=districtService.getByParent(parent);
        return new JsonResult<>(OK,list);
    }
}
