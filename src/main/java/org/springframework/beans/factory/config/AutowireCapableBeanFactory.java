package org.springframework.beans.factory.config;

import org.springframework.beans.factory.config.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/14 20:17
 * 4
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    private final List<AutowiredAnnotationBeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    @Override
    protected Object applyBeanPostProcessorBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        for (AutowiredAnnotationBeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            beanPostProcessor.setBeanFactory(this);
            result = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);

            if (Objects.isNull(result)) {
                return null;
            }
        }

        return result;
    }

    @Override
    protected Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName) {
        Object result = bean;
        for (AutowiredAnnotationBeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            result = beanPostProcessor.postProcessAfterInitialization(bean, beanName);

            if (Objects.isNull(result)) {
                return null;
            }
        }

        return result;
    }
}
