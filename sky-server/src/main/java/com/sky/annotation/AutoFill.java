package com.sky.annotation;

import com.sky.enumeration.OperationType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标识某个方法需要进行功能字段自动填充处理
 */
@Target(ElementType.METHOD) // 意思是这个注解只能用在方法上
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时依然存在，可以通过反射读取到
public @interface AutoFill {
    //数据库操作类型：UPDATE INSERT
    OperationType value();
}