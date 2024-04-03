package org.springframework.context;

import org.springframework.beans.BeanFactory;
import org.springframework.beans.SimpleBeanFactory;
import org.springframework.beans.XmlBeanDefinitionReader;
import org.springframework.core.ClassPathResource;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:07
 * 4
 */
public class ClassPathXmlApplicationContext {

    private final BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        ClassPathResource resource = new ClassPathResource(fileName);
        beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
    }

    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }
}
