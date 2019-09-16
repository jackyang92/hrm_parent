package com.ymy.hrm;

import com.ymy.hrm.util.SmsHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication9008.class)
public class SmsTest
{
    @Autowired
    private SmsHelper smsHelper;

    @Test
    public void test()throws Exception{
        smsHelper.sendSms("13198539137","1",new String[]{"8848","3"});
    }
}
