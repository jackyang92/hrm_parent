package com.ymy.hrm.service;

import com.ymy.hrm.util.AjaxResult;

public interface ImageValidateCodeService {
    String getCode(String uuid);

    AjaxResult validate(String codeUuid, String captcha);
}
