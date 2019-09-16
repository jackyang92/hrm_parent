package com.ymy.hrm.service;

import com.ymy.hrm.domain.CourseType;
import com.baomidou.mybatisplus.service.IService;
import com.ymy.hrm.query.CourseTypeQuery;
import com.ymy.hrm.util.PageList;

import java.util.List;
import java.util.Map;

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
    /**通过父亲id获取儿子及其儿子的儿子，子子孙孙
     * */
    List<CourseType> queryTypeTree(Long pid);
    //初始化课程站点主页
    void InitCourseSiteIndex();
    /**
     * 获取面包屑
     * @param courseTypeId
     * @return
     */
    List<Map<String, Object>> getCrumbs(Long courseTypeId);
}
