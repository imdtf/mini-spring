package org.springframework.beans.factory.support;

import org.springframework.beans.BeanDefinitionRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/14 20:20
 * 4
 */
public abstract class AbstractBeanFactory extends DefaultSingleBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private final Map<String, Object> earlySingletonObjects = new HashMap<>();

    public void refresh() {
        for (String beanName : beanDefinitionMap.keySet()) {
            getBean(beanName);
        }
    }

    @Override
    public Object getBean(String beanName) {
        try {
            Object bean = this.getSingleton(beanName);
            if (Objects.isNull(bean)) {
                bean = earlySingletonObjects.get(beanName);
                if (Objects.isNull(bean)) {
                    BeanDefinition beanDefinition = getBeanDefinition(beanName);
                    bean = createBean(beanDefinition);
                    registerBean(beanName, bean);
                    applyBeanPostProcessorBeforeInitialization(bean, beanName);
                    if (Objects.nonNull(beanDefinition.getInitMethodName()) && beanDefinition.getInitMethodName().length() > 0) {
                        invokeInitMethod(beanDefinition, bean);
                    }
                    applyBeanPostProcessorAfterInitialization(bean, beanName);
                }
            }
            return bean;
        } catch (Exception e) {
            throw new BeansException(e);
        }
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        if (Objects.isNull(beanDefinition)) {
            throw new BeansException();
        }
        Class<?> clazz = Class.forName(beanDefinition.getClassName());
        Object obj = this.doCreateBean(beanDefinition, clazz);
        this.earlySingletonObjects.put(beanDefinition.getId(), obj);
        this.handleProperties(beanDefinition, clazz, obj);
        return obj;
    }

    private Object doCreateBean(BeanDefinition beanDefinition, Class<?> clazz) throws Exception {
        Object bean;
        ConstructorArgumentValues argumentValues = beanDefinition.getConstructorArguments();
        if (!argumentValues.isEmpty()) {
            int argumentCount = argumentValues.getArgumentCount();
            Class<?>[] paramTypes = new Class<?>[argumentCount];
            Object[] paramValues = new Object[argumentCount];
            for (int i = 0; i < argumentCount; i++) {
                ConstructorArgumentValues.ArgumentValueHolder argumentValue = argumentValues.getArgumentValue(i);
                if (argumentValue.isRef()) {
                    Object dependentBean = getBean(argumentValue.getValue().toString());
                    paramValues[i] = dependentBean;
                    paramTypes[i] = dependentBean.getClass();
                } else {
                    String type = argumentValue.getType();
                    if ("java.lang.String".equals(type)) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    } else if ("int".equals(type)) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                    } else if ("java.lang.Integer".equals(type)) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                    } else {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }
            }
            Constructor<?> constructor = clazz.getConstructor(paramTypes);
            bean = constructor.newInstance(paramValues);
        } else {
            bean = clazz.newInstance();
        }

        return bean;
    }

    /**
     * 前置处理器
     * @param bean bean
     * @param beanName 名称
     * @return 处理后的bean
     */
    protected abstract Object applyBeanPostProcessorBeforeInitialization(Object bean, String beanName);

    private void invokeInitMethod(BeanDefinition beanDefinition, Object bean) throws Exception {
        Class<? extends BeanDefinition> clazz = beanDefinition.getClass();
        Method method = clazz.getMethod(beanDefinition.getInitMethodName());
        method.invoke(bean);
    }

    /**
     * 后置处理器
     * @param bean bean
     * @param beanName 名称
     * @return 处理后的bean
     */
    protected abstract Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName);

    @Override
    public boolean containsBean(String beanName) {
        return containsSingleton(beanName);
    }

    @Override
    public boolean isSingleton(String beanName) {
        return getBeanDefinition(beanName).isSingleton();
    }

    @Override
    public boolean isPrototype(String beanName) {
        return getBeanDefinition(beanName).isPrototype();
    }

    @Override
    public Class<?> getType(String beanName) {
        // TODO class
        return getBeanDefinition(beanName).getClass();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
        if (!beanDefinition.isLazyInit()) {
            getBean(name);
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return beanDefinitionMap.containsKey(name);
    }

    private void handleProperties(BeanDefinition beanDefinition, Class<?> clazz, Object bean) throws Exception {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.size(); i++) {
                PropertyValues.PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                Object value = propertyValue.getValue();
                if (propertyValue.isRef()) {
                    value = getBean(propertyValue.getValue().toString());
                }
                String methodName = "set" + propertyValue.getName().substring(0, 1).toUpperCase() + propertyValue.getName().substring(1);
                Method method = clazz.getMethod(methodName, value.getClass());
                method.invoke(bean, value);
            }
        }
    }

    public void registerBean(String beanName, Object bean) {
        registerSingleton(beanName, bean);
    }
}
