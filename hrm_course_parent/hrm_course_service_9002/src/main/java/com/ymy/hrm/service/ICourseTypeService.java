package com.ymy.hrm.service;

import com.ymy.hrm.domain.CourseType;
import com.baomidou.mybatisplus.service.IService;
import com.ymy.hrm.query.CourseTypeQuery;
import com.ymy.hrm.util.PageList;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author ymy
 * @since 2019-09-01
 */
public interface ICourseTypeService extends IService<CourseType> {

    PageList<CourseType> selectListPage(CourseTypeQuery query);
}
