package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipAddress;
import com.ymy.hrm.query.VipAddressQuery;
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
public class VipAddressClientHystrixFallbackFactory implements FallbackFactory<VipAddressClient> {

    @Override
    public VipAddressClient create(Throwable throwable) {
        return new VipAddressClient() {
            @Override
            public AjaxResult save(VipAddress vipAddress) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public VipAddress get(Long id) {
                return null;
            }

            @Override
            public List<VipAddress> list() {
                return null;
            }

            @Override
            public PageList<VipAddress> json(VipAddressQuery query) {
                return null;
            }
        };
    }
}
