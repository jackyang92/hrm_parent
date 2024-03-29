package com.ymy.hrm.service;

import com.ymy.hrm.CourseApplication9002;
import com.ymy.hrm.domain.CourseType;
import com.ymy.hrm.query.CourseTypeQuery;
import com.ymy.hrm.util.PageList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourseApplication9002.class)
public class ICourseTypeServiceTest {
    @Autowired
    private ICourseTypeService courseTypeService;

    @Test
    public void test()throws Exception{
        PageList<CourseType> page = courseTypeService.selectListPage(new CourseTypeQuery());
        System.out.println(page.getTotal());
        System.out.println(page.getRows().size());
        for (CourseType row : page.getRows()) {
            System.out.println(row);
            System.out.println(row.getParent());
            System.out.println("===========");
        }
    }
    @Test
    public void testQueryTypeTree()throws Exception{
        List<CourseType> courseTypes = courseTypeService.queryTypeTree(0L);
        for (CourseType courseType : courseTypes) {
            System.out.println(courseType);
            List<CourseType> children = courseType.getChildren();
            if (null != children){
                for (CourseType child : children) {
                    System.out.println(child);
                }
            }
        }
        
        }
    //初始化课程管理首页
    @Test
    public void testInitCourseSiteIndex()throws Exception{
        courseTypeService.InitCourseSiteIndex();
    }
}