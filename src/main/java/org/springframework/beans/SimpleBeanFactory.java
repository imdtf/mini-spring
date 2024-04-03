package org.springframework.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:39
 * 4
 */
public class SimpleBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private Map<String, Object> singletons = new HashMap<>();

    @Override
    public Object getBean(String beanName) {
        Object bean = singletons.get(beanName);
        if (Objects.isNull(bean)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (Objects.isNull(beanDefinition)) {
                throw new BeansException();
            }
            try {
                bean = Class.forName(beanDefinition.getClassName()).newInstance();
            } catch (Exception e) {
                throw new BeansException(e);
            }
            singletons.put(beanDefinition.getId(), bean);
        }
        return bean;
    }

    @Override
    public void registerBean(BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
    }
}
