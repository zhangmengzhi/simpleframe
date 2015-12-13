package org.zhangmz.simpleframe.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName:Action 
 * @Description:Action 方法注解
 * @author:张孟志
 * @date:2015年12月13日 下午4:46:31 
 * @version V1.0
 * 说明：在控制器类的方法上使用Action注解
 *      接受参数为“请求类型与路径”，例如：get:/user
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     * 请求类型与路径
     * 例如：get:/user
     */
    String value();
}
