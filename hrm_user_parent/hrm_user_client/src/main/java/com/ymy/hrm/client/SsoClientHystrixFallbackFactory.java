package com.ymy.hrm.client;

import com.ymy.hrm.domain.Sso;
import com.ymy.hrm.query.SsoQuery;
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
public class SsoClientHystrixFallbackFactory implements FallbackFactory<SsoClient> {

    @Override
    public SsoClient create(Throwable throwable) {
        return new SsoClient() {
            @Override
            public AjaxResult save(Sso sso) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Sso get(Long id) {
                return null;
            }

            @Override
            public List<Sso> list() {
                return null;
            }

            @Override
            public PageList<Sso> json(SsoQuery query) {
                return null;
            }
        };
    }
}
