package com.ymy.hrm.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.ymy.hrm.domain.Course;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ymy.hrm.query.CourseQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ymy
 * @since 2019-09-04
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> loadListPage(Page<Course> page, CourseQuery query);
}
