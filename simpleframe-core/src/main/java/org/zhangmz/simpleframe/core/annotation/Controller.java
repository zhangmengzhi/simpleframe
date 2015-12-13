package org.zhangmz.simpleframe.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName:Controller 
 * @Description:控制器注解
 * @author:张孟志
 * @date:2015年12月13日 下午4:48:57 
 * @version V1.0
 * 说明：在控制器类上使用
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
