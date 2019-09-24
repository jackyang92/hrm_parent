package com.ymy.hrm;

import com.ymy.hrm.domain.Sso;
import com.ymy.hrm.util.StrUtils;
import com.ymy.hrm.util.encrypt.MD5;
import org.junit.Test;

public class Md5Test {

    //注册时加密保存
    @Test
    public void testRegMd5En()throws Exception{

        String salt = StrUtils.getComplexRandomString(16);
        String secretyPwd = MD5.getMD5("yhptest"+salt);
        //加密的秘密和盐值都要存放到数据库
//        Qq7XiiDRCa8M0fGC
//                a87d151f6a908bd519a9b72482de9906

        System.out.println(salt);
        System.out.println(secretyPwd);
    }

    //登录时比对,也就是把输入的秘密加密和数据库查询出来的进行比对
    @Test
    public void testLonginEqual()throws Exception{

        String username ="xxx";
        String pwd = "yhptest";
        //通过用户名从数据库查询sso
        Sso sso = new Sso();
        sso.setSalt("Qq7XiiDRCa8M0fGC");
        sso.setPassword("a87d151f6a908bd519a9b72482de9906");

        String inputSecrutyPwd = MD5.getMD5(pwd+sso.getSalt());

        if (inputSecrutyPwd.equals(sso.getPassword())){
            System.out.println("登录成功!!!!!!!!!!!!!!!!!");
        }
    }
}
