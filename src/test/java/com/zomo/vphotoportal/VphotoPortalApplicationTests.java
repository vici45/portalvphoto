package com.zomo.vphotoportal;

import com.zomo.vphotoportal.common.Const;
import com.zomo.vphotoportal.service.imp.ProjectServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VphotoPortalApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(ProjectServiceImp.qiNiuCdnPrefix);
    }

}
