package com.ymy.hrm.service;

import com.ymy.hrm.domain.Course;
import com.baomidou.mybatisplus.service.IService;
import com.ymy.hrm.query.CourseQuery;
import com.ymy.hrm.util.PageList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ymy
 * @since 2019-09-04
 */
public interface ICourseService extends IService<Course> {
    /**
     * 分页+高级查询+关联查询
     * @param query
     * @return
     */
    PageList<Course> selectListPage(CourseQuery query);
}
