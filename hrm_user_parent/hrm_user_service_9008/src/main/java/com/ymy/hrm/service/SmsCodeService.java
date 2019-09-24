package com.ymy.hrm.service;

import com.ymy.hrm.util.AjaxResult;

public interface SmsCodeService {
      //发送验证码

    AjaxResult sendSmsCode(String mobile);
}
