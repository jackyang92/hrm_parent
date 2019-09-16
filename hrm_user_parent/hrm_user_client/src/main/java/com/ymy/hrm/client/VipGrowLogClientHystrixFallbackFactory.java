package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipGrowLog;
import com.ymy.hrm.query.VipGrowLogQuery;
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
public class VipGrowLogClientHystrixFallbackFactory implements FallbackFactory<VipGrowLogClient> {

    @Override
    public VipGrowLogClient create(Throwable throwable) {
        return new VipGrowLogClient() {
            @Override
            public AjaxResult save(VipGrowLog vipGrowLog) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public VipGrowLog get(Long id) {
                return null;
            }

            @Override
            public List<VipGrowLog> list() {
                return null;
            }

            @Override
            public PageList<VipGrowLog> json(VipGrowLogQuery query) {
                return null;
            }
        };
    }
}
