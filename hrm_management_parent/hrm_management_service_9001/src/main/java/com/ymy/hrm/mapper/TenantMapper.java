package com.ymy.hrm.mapper;

import com.ymy.hrm.domain.Tenant;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ymy
 * @since 2019-09-02
 */
public interface TenantMapper extends BaseMapper<Tenant> {
//保存机构所对应的套餐的中间表信息
    void saveTenantMeals(List<Map<String, Long>> mealsMap);
//删除中间表
    void removeTenantMeal(Serializable id);
}
