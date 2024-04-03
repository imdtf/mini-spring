package org.springframework.beans;

import lombok.RequiredArgsConstructor;
import org.dom4j.Element;
import org.springframework.core.Resource;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:35
 * 4
 */
@RequiredArgsConstructor
public class XmlBeanDefinitionReader {

    private final BeanFactory beanFactory;

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, className);
            beanFactory.registerBean(beanDefinition);
        }
    }
}
