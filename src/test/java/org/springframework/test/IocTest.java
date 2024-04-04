package org.springframework.test;

import org.junit.Test;
import org.springframework.context.ClassPathXmlApplicationContext;
import org.springframework.support.AService;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:19
 * 4
 */
public class IocTest {

    @Test
    public void testIoc1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = ((AService) context.getBean("a-service"));
        aService.sayHello();
    }
}
