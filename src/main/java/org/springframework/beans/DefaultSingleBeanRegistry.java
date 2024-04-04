package org.springframework.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:59
 * 4
 */
public class DefaultSingleBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletons = new HashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (singletons) {
            singletons.put(beanName, singletonObject);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return singletons.keySet().toArray(new String[0]);
    }

    @Override
    public int getSingletonCount() {
        return singletons.size();
    }

    public void removeSingleton(String beanName) {
        synchronized (singletons) {
            singletons.remove(beanName);
        }
    }
}
