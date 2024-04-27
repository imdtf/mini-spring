package org.springframework.support;

import lombok.Setter;
import org.springframework.beans.factory.config.annotation.Autowired;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2024/4/6 17:41
 * 4
 */
@Setter
public class CService {

    @Autowired
    private AService aService;
}
