package com.kingboot.basic.config.common;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p class="detail">
 * 功能:post data 参考数据
 * </p>
 * @author Kings
 * @ClassName King param.
 * @Version V1.0.
 * @date 2019.01.11 11:01:21
 */
@Target ( {ElementType.METHOD})
@Retention (RetentionPolicy.RUNTIME)
@Documented
public @interface KingParam {
    String value() default "";
    
    @AliasFor ("value") String data() default "";
}
