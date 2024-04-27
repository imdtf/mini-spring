package org.springframework.beans;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/4 17:17
 * 4
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册
     * @param name 名称
     * @param definition \
     */
    void registerBeanDefinition(String name, BeanDefinition definition);

    /**
     * 移除
     * @param name 名称
     */
    void removeBeanDefinition(String name);

    /**
     * 根据名称获取定义
     * @param name 名称
     * @return 定义
     */
    BeanDefinition getBeanDefinition(String name);

    /**
     * 是否包含
     * @param name 名称
     * @return 结果
     */
    boolean containsBeanDefinition(String name);
}
