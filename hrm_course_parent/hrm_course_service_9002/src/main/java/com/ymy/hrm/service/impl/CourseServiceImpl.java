package com.ymy.hrm.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.ymy.hrm.client.EsCourseClient;
import com.ymy.hrm.doc.EsCourse;
import com.ymy.hrm.domain.Course;
import com.ymy.hrm.mapper.CourseDetailMapper;
import com.ymy.hrm.mapper.CourseMapper;
import com.ymy.hrm.query.CourseQuery;
import com.ymy.hrm.service.ICourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ymy.hrm.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ymy
 * @since 2019-09-04
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private EsCourseClient esCourseClient;
    @Autowired
    private CourseMapper mapper;
    @Autowired
    private CourseDetailMapper detailMapper;
    @Override
    public PageList<Course> selectListPage(CourseQuery query) {
        Page<Course> page = new Page<>(query.getPage(),query.getRows());
        List<Course> rows =  mapper.loadListPage(page,query);
        return new PageList<>(page.getTotal(),rows);
    }

    @Override
    public void onLine(Long[] ids) {
        //在索引库批量添加数据
        List<Course> courseList = mapper.selectBatchIds(Arrays.asList(ids));
        List<EsCourse> esCourseList = courseList2EsCourse(courseList);
        esCourseClient.batchSave(esCourseList);
        //批量修改状态
        //update t_course set status = 1,start_time=xxx where id in (1,2,3)
        List<Map<String,Object>> idsMap = new ArrayList<>();
        if(ids!=null && ids.length>0){
            for (Long id : ids) {
                Map<String,Object> map = new HashMap<>();
                map.put("startTime",new Date());
                map.put("id",id);
                idsMap.add(map);
            }

        }
        mapper.batchOnline(idsMap);
    }

    @Override
    public void offLine(Long[] ids) {
        List<Course> courseList = mapper.selectBatchIds(Arrays.asList(ids));
        List<EsCourse> esCourseList = courseList2EsCourse(courseList);
        esCourseClient.batchDel(esCourseList);
        //批量修改状态
        //update t_course set status = 1,start_time=xxx where id in (1,2,3)
        mapper.batchOffline(Arrays.asList(ids));
    }

    private List<EsCourse> courseList2EsCourse(List<Course> courseList) {
        List<EsCourse> result = new ArrayList<>();
        for (Course course : courseList) {
            result.add(course2EsCourse(course));
        }
        return result;
    }

    // @TODO 不同服务,反3Fn设计冗余字段
    // @TODO 相同服务,关联查询
    private EsCourse course2EsCourse(Course course) {
        EsCourse  result = new EsCourse();
        result.setId(course.getId());
        result.setName(course.getName());
        result.setUsers(course.getUsers());
        result.setCourseTypeId(course.getCourseTypeId());
        //type-同库
        if (course.getCourseType() != null)
            result.setCourseTypeName(course.getCourseType().getName());
        //跨服务操作
        result.setGradeId(course.getGrade());
        result.setGradeName(null);
        result.setStatus(course.getStatus());
        result.setTenantId(course.getTenantId());
        result.setTenantName(course.getTenantName());
        result.setUserId(course.getUserId());
        result.setUserName(course.getUserName());
        result.setStartTime(course.getStartTime());
        result.setEndTime(course.getEndTime());
        //Detail
        result.setIntro(null);
        //resource
        result.setResources(null);
        //market
        result.setExpires(null);
        result.setPrice(null);
        result.setPriceOld(null);
        result.setQq(null);
        return result;
    }

    @Override
    public boolean insert(Course entity) {
        //课程表
        entity.setStatus(0); // tenantId tenantName userId userName
        mapper.insert(entity);
        //课程详情
        entity.getDetail().setCourseId(entity.getId());
        detailMapper.insert(entity.getDetail());
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {

        //删除数据库
        mapper.deleteById(id);
        //判断状态-还要删除索引库
        Course course = mapper.selectById(id);
        if (  course.getStatus() ==1)
            esCourseClient.delete(Integer.valueOf(id.toString()));
        return true;

    }

    @Override
    public boolean updateById(Course entity) {
        //修改数据库
        mapper.updateById(entity);
        //判断状态-还要修改索引库
        Course course = mapper.selectById(entity.getId());
        if ( course.getStatus() ==1)
            esCourseClient.save(course2EsCourse(entity));
        return true;
    }
}
