package com.ymy.hrm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ymy.hrm.domain.Employee;
import com.ymy.hrm.domain.Tenant;
import com.ymy.hrm.mapper.EmployeeMapper;
import com.ymy.hrm.mapper.TenantMapper;
import com.ymy.hrm.service.ITenantService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ymy
 * @since 2019-09-02
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {
    @Autowired
    private TenantMapper tenantMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public boolean insert(Tenant tenant) {
        tenant.setRegisterTime(new Date());//注册时间
        tenant.setState(false);//设置状态
        //添加机构
        tenantMapper.insert(tenant);
        //添加管理员
        Employee adminUser = tenant.getAdminUser();
        adminUser.setInputTime(new Date());//录入时间
        adminUser.setState(0);//正常
        adminUser.setType(true);//是否是租户管理员
        adminUser.setTenantId(tenant.getId());
        employeeMapper.insert(adminUser);
        //添加套餐中间表
        tenantMapper.saveTenantMeals(tenant.getMealsMap());
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        //删除机构
        tenantMapper.deleteById(id);
        //删除管理员
        Wrapper<Employee> wrapper = new EntityWrapper<>();
        wrapper.eq("tenant_id",id);
        employeeMapper.delete(wrapper);
        //删除套餐中间表
        tenantMapper.removeTenantMeal(id);
        return true;
    }

    @Override
    public boolean updateById(Tenant tenant) {
        //修改机构
        tenantMapper.updateById(tenant);
        //修改管理员
        employeeMapper.updateById(tenant.getAdminUser());
        //修改套餐中间表------->先删除，再添加
        tenantMapper.removeTenantMeal(tenant.getId());
        tenantMapper.saveTenantMeals(tenant.getMealsMap());

        return super.updateById(tenant);
    }
}
