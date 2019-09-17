package com.ky.gps.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionName {
    /**
     * 操作显示名
     */
    String displayName();

    /**
     * 操作组
     */
    String group();
}
