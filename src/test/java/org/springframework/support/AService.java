package org.springframework.support;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

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



    public void sayHello() {
        System.out.printf("A service say hello! name: %s, level: %d, property1: %s, property2: %s%n", name, level, property1, property2);
    }
}
