package com.ymy.hrm.service.impl;

import com.ymy.hrm.client.RedisClient;
import com.ymy.hrm.service.ImageValidateCodeService;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.VerifyCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageValidateCodeServiceImpl implements ImageValidateCodeService {
    @Autowired
    private RedisClient redisClient;
    @Override
    public String getCode(String uuid) {

        //随机生成6位验证码
        String code = VerifyCodeUtils.generateVerifyCode
                (6).toLowerCase();

        System.out.println(uuid);
        System.out.println(code);
        //写入redis
        redisClient.set(uuid,code,60);


        //输出到图片
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            VerifyCodeUtils.outputImage(100, 30, data, code);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //把图片加密返回
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }

    @Override
    public AjaxResult validate(String codeUuid, String captcha) {
        String code = redisClient.get(codeUuid);
        if(StringUtils.isBlank(code)){
            //只有过期
            return AjaxResult.me().setSuccess(false).setMessage("请输入正确图形验证码!");
        }
        if(!code.equals(captcha)){
            return AjaxResult.me().setSuccess(false).setMessage("请输入正确图形验证码!");

        }
        return AjaxResult.me();
    }
}
