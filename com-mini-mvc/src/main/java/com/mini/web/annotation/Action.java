package com.mini.web.annotation;

import com.mini.web.model.IModel;
import com.mini.web.model.ModelPage;

import java.lang.annotation.*;

/**
 * 控制器方法注解
 * @author xchao
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     * 返回数据类型
     * @return 数据类型
     */
    Class<? extends IModel<?>> value() default ModelPage.class;

    /**
     * 访问地址
     * @return 访问地址
     */
    String[] url() default {};

    /**
     * 视图路径
     * @return 视图路径
     */
    String path() default "";

    /**
     * 该方法支持的请求类型
     * @return 请求类型数组
     */
    Method[] method() default {Method.GET, Method.POST};

    /**
     * 扩展字段，允许用户自定义其含义
     * @return 自定义
     */
    String extend() default "";

    /**
     * 方法类型
     * @author xchao
     */
    enum Method {
        GET, POST
    }
}