package org.springframework.beans.factory;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:27
 * 4
 */
public interface BeanFactory {

    /**
     * 获取
     * @param beanName 名称
     * @return 结果
     */
    Object getBean(String beanName);

    /**
     * 是否包含
     * @param beanName 名称
     * @return 结果
     */
    boolean containsBean(String beanName);

    /**
     * 是否单例
     * @param beanName 名称
     * @return 结果
     */
    boolean isSingleton(String beanName);

    /**
     * 是否原型
     * @param beanName 名称
     * @return 结果
     */
    boolean isPrototype(String beanName);

    /**
     * 获取类型
     * @param beanName 名称
     * @return 结果
     */
    Class<?> getType(String beanName);
}
