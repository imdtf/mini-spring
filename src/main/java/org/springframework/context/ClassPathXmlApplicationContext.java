package org.springframework.context;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.ClassPathResource;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:07
 * 4
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {

    private final AutowireCapableBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        ClassPathResource resource = new ClassPathResource(fileName);
        beanFactory = new AutowireCapableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        if (isRefresh) {
            refresh();
        }
    }

    private void refresh() {
        registerBeanPostProcessor(this.beanFactory);
        onRefresh();
    }

    private void registerBeanPostProcessor(AutowireCapableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    private void onRefresh() {
        this.beanFactory.refresh();
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
