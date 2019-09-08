package com.ymy.hrm.repository;

import com.ymy.hrm.doc.EsCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CourseRepository extends ElasticsearchRepository<EsCourse,Long> {
}
