package org.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:03
 * 4
 */
@Data
@AllArgsConstructor
public class BeanDefinition {

    private String id;

    private String className;
}
