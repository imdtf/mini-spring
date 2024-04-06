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
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {

    private final SimpleBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        ClassPathResource resource = new ClassPathResource(fileName);
        beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        if (isRefresh) {
            beanFactory.refresh();
        }
    }

    @Override
    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    @Override
    public boolean containsBean(String beanName) {
        return beanFactory.containsBean(beanName);
    }

    @Override
    public boolean isSingleton(String beanName) {
        return beanFactory.isSingleton(beanName);
    }

    @Override
    public boolean isPrototype(String beanName) {
        return beanFactory.isPrototype(beanName);
    }

    @Override
    public Class<?> getType(String beanName) {
        return beanFactory.getType(beanName);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
    }
}
