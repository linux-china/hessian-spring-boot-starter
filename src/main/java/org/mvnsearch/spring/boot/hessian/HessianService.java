package org.mvnsearch.spring.boot.hessian;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * hessian service annotation
 *
 * @author linux_china
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HessianService {
    Class serviceInterface();
}
