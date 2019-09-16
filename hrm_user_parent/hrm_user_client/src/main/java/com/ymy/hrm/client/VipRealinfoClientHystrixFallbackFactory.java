package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipRealinfo;
import com.ymy.hrm.query.VipRealinfoQuery;
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
public class VipRealinfoClientHystrixFallbackFactory implements FallbackFactory<VipRealinfoClient> {

    @Override
    public VipRealinfoClient create(Throwable throwable) {
        return new VipRealinfoClient() {
            @Override
            public AjaxResult save(VipRealinfo vipRealinfo) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public VipRealinfo get(Long id) {
                return null;
            }

            @Override
            public List<VipRealinfo> list() {
                return null;
            }

            @Override
            public PageList<VipRealinfo> json(VipRealinfoQuery query) {
                return null;
            }
        };
    }
}
