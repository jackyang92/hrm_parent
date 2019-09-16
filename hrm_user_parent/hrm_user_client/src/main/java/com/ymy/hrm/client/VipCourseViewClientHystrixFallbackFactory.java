package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipCourseView;
import com.ymy.hrm.query.VipCourseViewQuery;
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
public class VipCourseViewClientHystrixFallbackFactory implements FallbackFactory<VipCourseViewClient> {

    @Override
    public VipCourseViewClient create(Throwable throwable) {
        return new VipCourseViewClient() {
            @Override
            public AjaxResult save(VipCourseView vipCourseView) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public VipCourseView get(Long id) {
                return null;
            }

            @Override
            public List<VipCourseView> list() {
                return null;
            }

            @Override
            public PageList<VipCourseView> json(VipCourseViewQuery query) {
                return null;
            }
        };
    }
}
