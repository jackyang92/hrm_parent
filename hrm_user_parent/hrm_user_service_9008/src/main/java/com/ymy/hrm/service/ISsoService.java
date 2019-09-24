package com.ymy.hrm.service;

import com.ymy.hrm.domain.Sso;
import com.baomidou.mybatisplus.service.IService;
import com.ymy.hrm.util.AjaxResult;

/**
 * <p>
 * 会员登录账号 服务类
 * </p>
 *
 * @author ymy
 * @since 2019-09-16
 */
public interface ISsoService extends IService<Sso> {
//校验用户是否存在
    AjaxResult validateSso(String mobile);
}
