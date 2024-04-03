package org.springframework.beans;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 21:25
 * 4
 */
public class BeansException extends RuntimeException {

    public BeansException() {}

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable t) {
        super(msg, t);
    }

    public BeansException(Throwable t) {
        super(t);
    }
}
