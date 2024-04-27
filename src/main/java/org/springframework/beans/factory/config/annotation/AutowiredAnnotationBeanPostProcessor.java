package org.springframework.beans.factory.config.annotation;

import lombok.Setter;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/14 20:16
 * 4
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Setter
    private AutowireCapableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if (isAutowired) {
                String fieldName = field.getName();
                Object autowiredObj = this.beanFactory.getBean(fieldName);
                field.setAccessible(true);
                try {
                    field.set(bean, autowiredObj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                System.out.println("autowire " + fieldName + " for bean " + beanName);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }
}
