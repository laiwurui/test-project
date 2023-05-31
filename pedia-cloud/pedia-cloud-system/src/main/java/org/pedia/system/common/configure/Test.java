package org.pedia.system.common.configure;

import org.pedia.core.utils.ApplicationUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test {
    @Bean
    public ApplicationUtil applicationUtil() {
        return new ApplicationUtil();
    }
}
