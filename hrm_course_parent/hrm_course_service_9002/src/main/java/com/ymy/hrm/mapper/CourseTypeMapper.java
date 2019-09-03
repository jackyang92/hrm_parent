package com.ymy.hrm.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.ymy.hrm.domain.CourseType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ymy.hrm.query.CourseTypeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程目录 Mapper 接口
 * </p>
 *
 * @author ymy
 * @since 2019-09-01
 */
public interface CourseTypeMapper extends BaseMapper<CourseType> {


    List<CourseType> loadListPage(Page page,@Param("query") CourseTypeQuery query);
}
