package org.springframework.beans.factory.config;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:56
 * 4
 */
public interface SingletonBeanRegistry {

    /**
     * 注册
     * @param beanName 名称
     * @param singletonObject bean 对象
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 获取
     * @param beanName 名称
     * @return 结果
     */
    Object getSingleton(String beanName);

    /**
     * 是否包含
     * @param beanName 名称
     * @return 结果
     */
    boolean containsSingleton(String beanName);

    /**
     * 获取所有名称
     * @return 结果
     */
    String[] getSingletonNames();

    /**
     * 获取数量
     * @return 结果
     */
    int getSingletonCount();
}
