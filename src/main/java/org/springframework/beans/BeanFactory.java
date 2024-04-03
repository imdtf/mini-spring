package org.springframework.beans;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:27
 * 4
 */
public interface BeanFactory {

    Object getBean(String beanName);

    void registerBean(BeanDefinition beanDefinition);
}