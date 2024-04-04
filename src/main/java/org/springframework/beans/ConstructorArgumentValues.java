package org.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/4 16:49
 * 4
 */
public class ConstructorArgumentValues {

    private final List<ArgumentValueHolder> genericArgumentValues = new ArrayList<>();

    public void addArgumentValue(ArgumentValueHolder newValue) {
        genericArgumentValues.add(newValue);
    }

    public boolean isEmpty() {
        return genericArgumentValues.isEmpty();
    }

    public int getArgumentCount() {
        return genericArgumentValues.size();
    }

    public ArgumentValueHolder getArgumentValue(int index) {
        return genericArgumentValues.get(index);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ArgumentValueHolder {

        private String type;

        private String name;

        private Object value;
    }
}
