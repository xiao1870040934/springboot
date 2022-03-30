package cn.ccsu.store.mapper;

import cn.ccsu.store.entity.District;

import java.util.List;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/30 19:35
 * @Version 1.0
 */
public interface DistrictMapper {

    /**
     * 根据父代号查询标签
     * @param parent 父代号
     * @return 某个父代号区域下的所有区域列表
     */
    List<District> findByParent(String parent);

    /**
     * 通过当前code来获取当前省市区的名称
     * @param code 代号
     * @return 对应的省市区名称
     */
    String findNameByCode(String code);
}
