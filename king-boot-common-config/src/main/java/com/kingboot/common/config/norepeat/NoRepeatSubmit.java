package com.kingboot.common.config.norepeat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p class="detail">
 * 功能:防止重复提交标记注解
 * </p>
 * @author Kings
 * @ClassName No repeat submit.
 * @Version V1.0.
 * @date 2019.06.10 16:11:49
 */
@Target (ElementType.METHOD) // 作用到方法上
@Retention (RetentionPolicy.RUNTIME) // 运行时有效
public @interface NoRepeatSubmit {
}