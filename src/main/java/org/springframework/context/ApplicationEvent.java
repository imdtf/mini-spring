package org.springframework.context;

import java.util.EventObject;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 22:29
 * 4
 */
public class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
