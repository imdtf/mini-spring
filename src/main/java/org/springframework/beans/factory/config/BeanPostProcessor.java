package org.springframework.beans.factory.config;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/14 20:09
 * 4
 */
public interface BeanPostProcessor {

    /**
     * 前置处理
     * @param bean \
     * @param beanName 名称
     * @return 处理后的 bean
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * 后置处理
     * @param bean \
     * @param beanName 名称
     * @return 处理后的 bean
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
