package org.zhangmz.simpleframe.core.helper;

import java.lang.reflect.Field;
import java.util.Map;
import org.zhangmz.simpleframe.core.annotation.Inject;
import org.zhangmz.simpleframe.core.util.ArrayUtil;
import org.zhangmz.simpleframe.core.util.CollectionUtil;
import org.zhangmz.simpleframe.core.util.ReflectionUtil;

/**
 * 
 * @ClassName:IocHelper 
 * @Description:依赖注入助手类
 * @author:张孟志
 * @date:2015年12月13日 下午7:09:45 
 * @version V1.0
 * 说明： 将带有 Inject 注解的成员变量注入。
 *      在系统启动（simpleframe开始工作）时加载这个IocHelper。
 */
public final class IocHelper {

    static {
    	// 获取所有的Bean类与Bean实例之间的映射关系（Bean Map）
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
            	// 从BeanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取Bean类定义的所有成员变量（Bean Field）
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                    	// 判断当前Bean Field是否带有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                            	// 通过反射初始化 BeanField 的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
