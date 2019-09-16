package com.ymy.hrm.web.controller;

import com.ymy.hrm.service.ImageValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图形验证码
 */
@RestController
@RequestMapping("/imgCode")
public class ImageValidateCodeController {

    @Autowired
    private ImageValidateCodeService service;
    //获取验证码
    @GetMapping
    public String getCode(String uuid){
        return service.getCode(uuid);
    }
}
