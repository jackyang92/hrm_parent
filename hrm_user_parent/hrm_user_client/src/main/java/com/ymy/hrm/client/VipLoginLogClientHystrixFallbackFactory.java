package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipLoginLog;
import com.ymy.hrm.query.VipLoginLogQuery;
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
public class VipLoginLogClientHystrixFallbackFactory implements FallbackFactory<VipLoginLogClient> {

    @Override
    public VipLoginLogClient create(Throwable throwable) {
        return new VipLoginLogClient() {
            @Override
            public AjaxResult save(VipLoginLog vipLoginLog) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public VipLoginLog get(Long id) {
                return null;
            }

            @Override
            public List<VipLoginLog> list() {
                return null;
            }

            @Override
            public PageList<VipLoginLog> json(VipLoginLogQuery query) {
                return null;
            }
        };
    }
}
