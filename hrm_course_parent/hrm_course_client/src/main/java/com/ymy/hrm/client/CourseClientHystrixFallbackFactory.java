package com.ymy.hrm.client;

import com.ymy.hrm.domain.Course;
import com.ymy.hrm.query.CourseQuery;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yaohuaipeng
 * @date 2018/10/8-16:18
 */
@Component
public class CourseClientHystrixFallbackFactory implements FallbackFactory<CourseClient> {

    @Override
    public CourseClient create(Throwable throwable) {
        return new CourseClient() {
            @Override
            public AjaxResult save(Course course) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Course get(Long id) {
                return null;
            }

            @Override
            public List<Course> list() {
                return null;
            }

            @Override
            public PageList<Course> json(CourseQuery query) {
                return null;
            }
        };
    }
}
