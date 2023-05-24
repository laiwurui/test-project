package org.pedia.core.annotation;

import org.pedia.core.configure.CommonComponentRegister;
import org.springframework.context.annotation.Import;

@Import(CommonComponentRegister.class)
public @interface EnableCommonConfig {
}
