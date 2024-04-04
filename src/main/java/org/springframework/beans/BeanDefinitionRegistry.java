package org.springframework.beans;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/4 17:17
 * 4
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition definition);

    void removeBeanDefinition(String name);

    BeanDefinition getBeanDefinition(String name);

    boolean containsBeanDefinition(String name);
}
