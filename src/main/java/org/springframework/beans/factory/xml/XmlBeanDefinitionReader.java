package org.springframework.beans.factory.xml;

import lombok.RequiredArgsConstructor;
import org.dom4j.Element;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.core.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.beans.PropertyValues.PropertyValue;
import static org.springframework.beans.factory.config.ConstructorArgumentValues.ArgumentValueHolder;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:35
 * 4
 */
@RequiredArgsConstructor
public class XmlBeanDefinitionReader {

    private final AutowireCapableBeanFactory beanFactory;

    @SuppressWarnings("unchecked")
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, className);

            PropertyValues propertyValues = new PropertyValues();
            List<Element> propertyElements = element.elements("property");
            List<String> refNames = new ArrayList<>();
            for (Element propertyElement : propertyElements) {
                String ref = propertyElement.attributeValue("ref");
                boolean isRef;
                if (Objects.nonNull(ref) && !"".equals(ref)) {
                    isRef = true;
                    refNames.add(ref);
                } else {
                    isRef = false;
                }
                propertyValues.addPropertyValue(new PropertyValue(propertyElement.attributeValue("name"), isRef ? ref : propertyElement.attributeValue("value"), isRef));
            }
            beanDefinition.setDependsOn(refNames.toArray(new String[0]));
            beanDefinition.setPropertyValues(propertyValues);

            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            List<Element> constructorArgElements = element.elements("constructor-arg");
            for (Element e : constructorArgElements) {
                String ref = e.attributeValue("ref");
                boolean isRef = Objects.nonNull(ref) && !ref.isEmpty();
                constructorArgumentValues.addArgumentValue(new ArgumentValueHolder(e.attributeValue("type"), e.attributeValue("name"), isRef ? ref : e.attributeValue("value"), isRef));
            }
            beanDefinition.setConstructorArguments(constructorArgumentValues);

            beanFactory.registerBeanDefinition(id, beanDefinition);
        }
    }
}
