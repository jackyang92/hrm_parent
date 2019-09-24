package com.ymy.hrm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ymy.hrm.domain.Sso;
import com.ymy.hrm.mapper.SsoMapper;
import com.ymy.hrm.service.ISsoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ymy.hrm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员登录账号 服务实现类
 * </p>
 *
 * @author ymy
 * @since 2019-09-16
 */
@Service
public class SsoServiceImpl extends ServiceImpl<SsoMapper, Sso> implements ISsoService {
    @Autowired
    private SsoMapper ssoMapper;

    @Override
    public AjaxResult validateSso(String mobile) {
        List<Sso> ssos =
                ssoMapper.selectList(new EntityWrapper<Sso>().eq("phone", mobile));
        if (ssos != null && ssos.size() > 0)
            return AjaxResult.me().setSuccess(false).setMessage("用户已存在!直接登录或者找回密码继续使用!");
        return AjaxResult.me();
    }
}
