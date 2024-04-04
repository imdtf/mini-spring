package org.springframework.beans;

import lombok.Data;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:03
 * 4
 */
@Data
public class BeanDefinition {

    private static final String SCOPE_SINGLETON = "singleton";

    private static final String SCOPE_PROTOTYPE = "prototype";

    private boolean lazyInit;

    private String[] dependsOn;

    private ConstructorArgumentValues constructorArguments;

    private PropertyValues propertyValues;

    private String initMethodName;

    private volatile Object beanClass;

    private String id;

    private String className;

    private String scope = SCOPE_SINGLETON;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public boolean isSingleton() {
        return scope.equals(SCOPE_PROTOTYPE);
    }

    public boolean isPrototype() {
        return scope.equals(SCOPE_PROTOTYPE);
    }
}
