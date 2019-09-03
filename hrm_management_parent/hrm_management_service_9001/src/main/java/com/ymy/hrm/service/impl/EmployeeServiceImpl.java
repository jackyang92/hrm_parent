package com.ymy.hrm.service.impl;

import com.ymy.hrm.domain.Employee;
import com.ymy.hrm.mapper.EmployeeMapper;
import com.ymy.hrm.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ymy
 * @since 2019-09-02
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
