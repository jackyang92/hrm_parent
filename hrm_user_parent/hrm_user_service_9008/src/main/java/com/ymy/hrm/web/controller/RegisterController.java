package com.ymy.hrm.web.controller;

import com.ymy.hrm.service.ISsoService;
import com.ymy.hrm.service.ImageValidateCodeService;
import com.ymy.hrm.service.SmsCodeService;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.SmsHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private ImageValidateCodeService imageValidateCodeService;
    @Autowired
    private ISsoService ssoService;
    @Autowired
    private SmsCodeService smsCodeService;
    @Autowired
    private SmsHelper helper;

    @RequestMapping("/sendSmsCode")
    public AjaxResult sendSmsCode(@RequestBody Map<String, Object> params) {
        String codeUuid = (String) params.get("codeUuid");
        String captcha = (String) params.get("captcha");//验证码
        String mobile = (String) params.get("mobile");
        //参数合法性校验
        if(StringUtils.isBlank(mobile)||StringUtils.isBlank(captcha)||StringUtils.isBlank(codeUuid)){
        return AjaxResult.me().setSuccess(false).setMessage("系统错误，请重新输入");
        }
        //图形验证码的校验
        AjaxResult ajaxResult = imageValidateCodeService.validate(codeUuid,captcha);
        if(!ajaxResult.isSuccess())
            return ajaxResult;
        //手机用户的校验，是否存在
        ajaxResult = ssoService.validateSso(mobile);
        if (!ajaxResult.isSuccess())
            return ajaxResult;
        //短信验证码
        return smsCodeService.sendSmsCode(mobile);

    }
}
