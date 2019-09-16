package com.ymy.hrm.client;

import com.ymy.hrm.domain.PageConfig;
import com.ymy.hrm.query.PageConfigQuery;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author yaohuaipeng
 * @date 2018/10/8-16:18
 */
@Component
public class PageConfigClientHystrixFallbackFactory implements FallbackFactory<PageConfigClient> {

    @Override
    public PageConfigClient create(Throwable throwable) {
        return new PageConfigClient() {
            @Override
            public AjaxResult save(PageConfig pageConfig) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public PageConfig get(Long id) {
                return null;
            }

            @Override
            public List<PageConfig> list() {
                return null;
            }

            @Override
            public PageList<PageConfig> json(PageConfigQuery query) {
                return null;
            }

            @Override
            public AjaxResult staticPage(Map<String, String> map) {
                return null;
            }
        };
    }
}
