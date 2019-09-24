package com.ymy.hrm.service.impl;

import com.ymy.hrm.client.RedisClient;
import com.ymy.hrm.service.SmsCodeService;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.SmsHelper;
import com.ymy.hrm.util.StrUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class SmsCodeServiceImpl implements SmsCodeService {
    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SmsHelper smsHelper;

    @Override
    public AjaxResult sendSmsCode(String mobile) {
        String smsCode = StrUtils.getRandomString(6); //默认是过期生成验证码
        String key = "smsCode-" + mobile;
        String smsCodeByRedis = redisClient.get(key); //code:time
        if (StringUtils.isNotBlank(smsCodeByRedis)) {
                //有验证码
            //校验是否已经过重发时间
            String recoredTime  = smsCodeByRedis.split(":")[1];
             smsCode  = smsCodeByRedis.split(":")[0];
             if(new Date().getTime()-Long.valueOf(recoredTime)<1*60*1000){
                 //当前时间-记录时间=间隔时间,如果小于1分钟说明没有过重发时间
                 return AjaxResult.me().setSuccess(false).setMessage("请不要频繁操作!");
             }
        }
        redisClient.set(key,smsCode+":"+new Date().getTime(),3*60);

        //发送短信验证码;
        //smsHelper.sendSms(mobile,"1",new String[]{smsCode,"3"});
        System.out.println("已经向"+mobile+"发送了验证码:"+smsCode);
        //没有验证码-记录验证码
        return AjaxResult.me();

          /*
        String key = "smsCode-" + mobile;
        String smsCodeByRedis = redisClient.get(key);
        if (StringUtils.isNotBlank(smsCodeByRedis)){
            //有验证码
            //校验是否已经过重发时间
            String recoredTime = smsCodeByRedis.split(":")[1];
            String smsCode = smsCodeByRedis.split(":")[0];
            if (new Date().getTime()-Long.valueOf(recoredTime)<1*60*1000){
                //当前时间-记录时间=间隔时间,如果小于1分钟说明没有过重发时间
                return AjaxResult.me().setSuccess(false).setMessage("请不要频繁操作!");
            }

            redisClient.set(key,smsCode+":"+new Date().getTime()); //code:time

            //发送短信验证码;
            //smsHelper.sendSms(mobile,"1",new String[]{smsCode,"3"});
            System.out.println("已经向"+mobile+"发送了验证码:"+smsCode);
            //没有验证码-记录验证码
            return AjaxResult.me();
        }
        else{//验证码过期
            String smsCode = StrUtils.getRandomString(6); //默认是过期生成验证码
            redisClient.set(key,smsCode+":"+new Date().getTime()); //code:time

            //发送短信验证码;
            //smsHelper.sendSms(mobile,"1",new String[]{smsCode,"3"});
            System.out.println("已经向"+mobile+"发送了验证码:"+smsCode);
            //没有验证码-记录验证码
            return AjaxResult.me();
        }*/

    }
}
