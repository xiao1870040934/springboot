package cn.ccsu.store.service;

import cn.ccsu.store.entity.District;
import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/30 20:18
 * @Version 1.0
 */
public interface IDistrictService {

    /**
     * 根据父代号来查询区域信息（省市区）
     * @param parent 父代号
     * @return 多个区域的信息
     */
    List<District> getByParent(String parent);
}
