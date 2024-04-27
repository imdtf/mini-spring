package org.springframework.context;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/3 22:29
 * 4
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     * @param event 事件
     */
    void publishEvent(ApplicationEvent event);
}
