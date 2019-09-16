package com.ymy.hrm.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ymy.hrm.cache.CourseTypeCache;
import com.ymy.hrm.client.PageConfigClient;
import com.ymy.hrm.client.RedisClient;
import com.ymy.hrm.domain.CourseType;
import com.ymy.hrm.mapper.CourseTypeMapper;
import com.ymy.hrm.query.CourseTypeQuery;
import com.ymy.hrm.service.ICourseTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ymy.hrm.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 * @author ymy
 * @since 2019-09-01
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {
    @Autowired
    private CourseTypeMapper courseTypeMapper;
    @Autowired
    private CourseTypeCache courseTypeCache;
    @Autowired
    private RedisClient redisClient;
    @Override
    public PageList<CourseType> selectListPage(CourseTypeQuery query) {
        Page page = new Page(query.getPage(), query.getRows());
        List<CourseType> courseTypes = courseTypeMapper.loadListPage(page, query);
        return new PageList<>(page.getTotal(), courseTypes);
    }

    @Override
    public List<CourseType> queryTypeTree(Long pid) { // -1

        List<CourseType> courseTypes = courseTypeCache.getCourseTypes();
        if (courseTypes== null || courseTypes.size()<1){
            System.out.println("db...............");
            //递归
            // return getCourseTypesRecursion(pid);
            //循环
            List<CourseType> courseTypesDb = getCourseTypesLoop(pid);
            if (courseTypesDb==null|| courseTypesDb.size()<1)
                courseTypesDb = new ArrayList<>();
            courseTypeCache.setCourseTypes(courseTypesDb);//[]
            return courseTypesDb;
        }

        System.out.println("cache...............");
        return  courseTypes;
       // return getCourseTypesLoop(pid);
    }

    @Autowired
    private PageConfigClient pageConfigClient;
    @Override
    public void InitCourseSiteIndex() {
        //1 准备模板,并且上传fastdfs
        //2存放数据到redis
        List<CourseType> courseTypes = queryTypeTree(0L);
        String dataKey = "courseTypes";
        redisClient.set(dataKey, JSONArray.toJSONString(courseTypes));
        //3调用静态化接口产生静态页面,并且放入fastdfs
        String pageName = "CourseIndex";
        //本来应该通过PageName获取page后设置pageconfig传递,由于数据在查询端,还不如直接传入pageName到那边查询.
        Map<String,String> map = new HashMap<>();
        map.put("dataKey",dataKey);
        map.put("pageName",pageName);
        pageConfigClient.staticPage(map);
        //4往消息队列放一个消息,让pageAgent来下载静态页面
    }

    @Override
    public List<Map<String, Object>> getCrumbs(Long courseTypeId) {

        List<Map<String,Object>> result = new ArrayList<>();
        //1 获取path 1.2.3
        CourseType courseType = courseTypeMapper.selectById(courseTypeId);
        String path = courseType.getPath();

        //2 截取path中各个节点自己  1 2 3
        String[] paths = path.split("\\.");

        //3 获取自己节点兄弟封装Map,放入List中进行返回
        for (String ownerIdStr : paths) {
            Map<String,Object> map = new HashMap<>();

            Long ownerId = Long.valueOf(ownerIdStr);

            System.out.println(ownerId);
            //获取每个自己
            CourseType owner =  courseTypeMapper.selectById(ownerId);
            map.put("owner",owner);
            //查询兄弟
            //获取父亲所有儿子
            List<CourseType> allChildren = courseTypeMapper
                    .selectList(new EntityWrapper<CourseType>().eq("pid",owner.getPid()));

            //干掉自己-边遍历边操作(正删改),要用迭代器
            Iterator<CourseType> iterator = allChildren.iterator();
            while (iterator.hasNext()){
                CourseType currentType = iterator.next();
                if (currentType.getId().longValue()==owner.getId().longValue()){
                    iterator.remove();
                    continue; //跳出当前循环
                }
            }
            map.put("otherCourseTypes",allChildren);
            result.add(map);
        }
        return result;
    }


    public static void main(String[] args) {
        String path = "1.2.3";

        //2 截取path中各个节点自己  1 2 3
        String[] paths = path.split("\\.");
        for (String s : paths) {
            System.out.println(s);
        }
        System.out.println(paths.length);
    }
    /**
     * 方案2:循环方案:一次sql
     * @param pid 0
     * @return
     */
    private List<CourseType> getCourseTypesLoop(Long pid) { //0

        System.out.println(pid);
        List<CourseType> result = new ArrayList<>();
        //1 查询所有类型
        List<CourseType> allTypes = courseTypeMapper.selectList(null);
        for (CourseType allType : allTypes) {
            System.out.println(allType);
        }
        //建立id和CourseType的关联关系
        Map<Long,CourseType> allTypesDto = new HashMap<>();
        for (CourseType allType : allTypes) {
            allTypesDto.put(allType.getId(),allType);
        }
        //2 遍历判断是否是第一级  pid为传入id,
        for (CourseType type : allTypes) {
            Long pidTmp = type.getPid();
            System.out.println(pidTmp);
            //2.1是直接加入返回列表
            if (pidTmp.longValue()== pid.longValue()){
                result.add(type);
            }else{
                //方案2:通过map获取
                CourseType parent = allTypesDto.get(pidTmp);
                System.out.println(parent);
                //获取父亲儿子集合,把自己加进去
                parent.getChildren().add(type);
            }
        }
        return result;
    }
    /**
     * 方案1:递归,每次都要发送sql效率低下
     * @param pid
     * @return
     */
    private List<CourseType> getCourseTypesRecursion(Long pid) {
        //方案1:递归-自己调用自己,要有出口
        List<CourseType> children = courseTypeMapper
                .selectList(new EntityWrapper<CourseType>().eq("pid", pid));
        //要有出口
        if (children==null || children.size()<1)
        {
            return null;
        }
        for (CourseType child : children) {
            //自己调用自己
            List<CourseType> courseTypes = queryTypeTree(child.getId());
            child.setChildren(courseTypes);
        }
        return children;
    }
    @Override
    public boolean insert(CourseType entity) {
        courseTypeMapper.insert(entity);
        List<CourseType> courseTypes = queryTypeTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        courseTypeMapper.deleteById(id);
        List<CourseType> courseTypes = queryTypeTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        courseTypeMapper.updateById(entity);
        List<CourseType> courseTypes = queryTypeTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }
}

