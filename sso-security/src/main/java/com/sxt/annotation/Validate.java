package com.sxt.annotation;

import org.aspectj.lang.annotation.Aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/1/2
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
}
