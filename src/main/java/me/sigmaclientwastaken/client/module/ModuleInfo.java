package me.sigmaclientwastaken.client.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    String name();
    Category category();
    int key() default 0;
    boolean enabled() default false;
}
