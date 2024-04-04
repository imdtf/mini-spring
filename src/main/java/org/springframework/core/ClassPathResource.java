package org.springframework.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:31
 * 4
 */
public class ClassPathResource implements Resource {

    private final Iterator<Element> elementIterator;

    @SuppressWarnings("unchecked")
    public ClassPathResource(String fileName) {
        SAXReader saxReader = new SAXReader();
        URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        Document document;
        try {
            document = saxReader.read(xmlPath);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        this.elementIterator = document.getRootElement().elementIterator();
    }

    @Override
    public boolean hasNext() {
        return elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return elementIterator.next();
    }
}
