package cn.ccsu.store.service.impl;

import cn.ccsu.store.entity.District;
import cn.ccsu.store.mapper.DistrictMapper;
import cn.ccsu.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/30 20:35
 * @Version 1.0
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list=districtMapper.findByParent(parent);
        for (District d:list) {
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }
}
