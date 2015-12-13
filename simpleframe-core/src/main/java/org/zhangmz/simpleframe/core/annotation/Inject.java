package org.zhangmz.simpleframe.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName:Inject 
 * @Description:依赖注入注解
 * @author:张孟志
 * @date:2015年12月13日 下午4:50:55 
 * @version V1.0
 * 说明：在控制器中可以使用Inject注解将服务类注入进来。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
