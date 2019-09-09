package com.ymy.hrm.client;

import com.ymy.hrm.domain.Site;
import com.ymy.hrm.query.SiteQuery;
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
public class SiteClientHystrixFallbackFactory implements FallbackFactory<SiteClient> {

    @Override
    public SiteClient create(Throwable throwable) {
        return new SiteClient() {
            @Override
            public AjaxResult save(Site site) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Site get(Long id) {
                return null;
            }

            @Override
            public List<Site> list() {
                return null;
            }

            @Override
            public PageList<Site> json(SiteQuery query) {
                return null;
            }
        };
    }
}
