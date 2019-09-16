package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipBase;
import com.ymy.hrm.query.VipBaseQuery;
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
public class VipBaseClientHystrixFallbackFactory implements FallbackFactory<VipBaseClient> {

    @Override
    public VipBaseClient create(Throwable throwable) {
        return new VipBaseClient() {
            @Override
            public AjaxResult save(VipBase vipBase) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public VipBase get(Long id) {
                return null;
            }

            @Override
            public List<VipBase> list() {
                return null;
            }

            @Override
            public PageList<VipBase> json(VipBaseQuery query) {
                return null;
            }
        };
    }
}
