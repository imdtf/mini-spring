package org.springframework.support;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:18
 * 4
 */
@RequiredArgsConstructor
@Setter
public class AService {

    private final String name;

    private final int level;

    private String property1;

    private String property2;

    private BService bService;



    public void sayHello() {
        Class<? extends AService> clazz = this.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                System.out.println(declaredField.getName() + ": " + declaredField.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A service say hello!");
    }
}
