package com.ymy.hrm.service.impl;

import com.ymy.hrm.ManagementApplication9001;
import com.ymy.hrm.domain.Systemdictionary;
import com.ymy.hrm.service.ISystemdictionaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagementApplication9001.class)
public class SystemdictionaryitemServiceImplTest {
    @Autowired
    private ISystemdictionaryService systemdictionaryService;
    @Test
    public void test()throws Exception{
        for (Systemdictionary systemdictionary : systemdictionaryService.selectList(null)) {
            System.out.println(systemdictionary);
        }

        }
}