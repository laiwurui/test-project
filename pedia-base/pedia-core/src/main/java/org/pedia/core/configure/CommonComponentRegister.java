package org.pedia.core.configure;

import org.pedia.core.utils.ApplicationUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class CommonComponentRegister {

    @Bean
    @ConditionalOnMissingBean(name = "globalExceptionHandler")
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public ApplicationUtil applicationUtil() {
        return new ApplicationUtil();
    }
}
