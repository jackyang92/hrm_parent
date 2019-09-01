package com.ymy.hrm.client;

import com.ymy.hrm.domain.Systemdictionaryitem;
import com.ymy.hrm.query.SystemdictionaryitemQuery;
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
public class SystemdictionaryitemClientHystrixFallbackFactory implements FallbackFactory<SystemdictionaryitemClient> {

    @Override
    public SystemdictionaryitemClient create(Throwable throwable) {
        return new SystemdictionaryitemClient() {
            @Override
            public AjaxResult save(Systemdictionaryitem systemdictionaryitem) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Systemdictionaryitem get(Long id) {
                return null;
            }

            @Override
            public List<Systemdictionaryitem> list() {
                return null;
            }

            @Override
            public PageList<Systemdictionaryitem> json(SystemdictionaryitemQuery query) {
                return null;
            }
        };
    }
}
