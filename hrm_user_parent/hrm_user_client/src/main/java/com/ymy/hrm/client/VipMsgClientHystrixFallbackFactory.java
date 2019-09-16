package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipMsg;
import com.ymy.hrm.query.VipMsgQuery;
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
public class VipMsgClientHystrixFallbackFactory implements FallbackFactory<VipMsgClient> {

    @Override
    public VipMsgClient create(Throwable throwable) {
        return new VipMsgClient() {
            @Override
            public AjaxResult save(VipMsg vipMsg) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public VipMsg get(Long id) {
                return null;
            }

            @Override
            public List<VipMsg> list() {
                return null;
            }

            @Override
            public PageList<VipMsg> json(VipMsgQuery query) {
                return null;
            }
        };
    }
}
