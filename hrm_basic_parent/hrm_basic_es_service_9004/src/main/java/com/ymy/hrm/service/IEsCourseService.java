package com.ymy.hrm.service;

import com.ymy.hrm.doc.EsCourse;
import com.ymy.hrm.query.EsCourseQuery;
import com.ymy.hrm.util.PageList;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ymy
 * @since 2019-09-05
 */
public interface IEsCourseService  {

    void updateById(EsCourse esCourse);

    void insert(EsCourse esCourse);

    void deleteById(Long id);

    EsCourse selectById(Long id);

    List<EsCourse> selectList(Object o);

    PageList<EsCourse> selectListPage(EsCourseQuery query);

    void batchSave(List<EsCourse> esCourseList);

    void batchDel(List<EsCourse> esCourseList);
}
