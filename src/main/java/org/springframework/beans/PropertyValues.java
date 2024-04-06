package org.springframework.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/4 16:51
 * 4
 */
@Getter
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public boolean isEmpty() {
        return propertyValueList.isEmpty();
    }

    public int size() {
        return propertyValueList.size();
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    @Getter
    @RequiredArgsConstructor
    public static class PropertyValue {

        private final String name;

        private final Object value;

        private final boolean isRef;
    }
}
