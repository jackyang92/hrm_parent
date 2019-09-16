package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipCourseCollect;
import com.ymy.hrm.query.VipCourseCollectQuery;
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
public class VipCourseCollectClientHystrixFallbackFactory implements FallbackFactory<VipCourseCollectClient> {

    @Override
    public VipCourseCollectClient create(Throwable throwable) {
        return new VipCourseCollectClient() {
            @Override
            public AjaxResult save(VipCourseCollect vipCourseCollect) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public VipCourseCollect get(Long id) {
                return null;
            }

            @Override
            public List<VipCourseCollect> list() {
                return null;
            }

            @Override
            public PageList<VipCourseCollect> json(VipCourseCollectQuery query) {
                return null;
            }
        };
    }
}
