package org.springframework.beans;

import lombok.RequiredArgsConstructor;
import org.dom4j.Element;
import org.springframework.core.Resource;

import java.util.List;

import static org.springframework.beans.PropertyValues.PropertyValue;
import static org.springframework.beans.ConstructorArgumentValues.ArgumentValueHolder;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:35
 * 4
 */
@RequiredArgsConstructor
public class XmlBeanDefinitionReader {

    private final SimpleBeanFactory beanFactory;

    @SuppressWarnings("unchecked")
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, className);

            PropertyValues propertyValues = new PropertyValues();
            List<Element> propertyElements = element.elements("property");
            for (Element propertyElement : propertyElements) {
                propertyValues.addPropertyValue(new PropertyValue(propertyElement.attributeValue("name"), propertyElement.attributeValue("value")));
            }
            beanDefinition.setPropertyValues(propertyValues);

            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            List<Element> constructorArgElements = element.elements("constructor-arg");
            for (Element e : constructorArgElements) {
                constructorArgumentValues.addArgumentValue(new ArgumentValueHolder(e.attributeValue("type"), e.attributeValue("name"), e.attributeValue("value")));
            }
            beanDefinition.setConstructorArguments(constructorArgumentValues);

            beanFactory.registerBeanDefinition(id, beanDefinition);
        }
    }
}
