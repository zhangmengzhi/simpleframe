package org.zhangmz.simpleframe.core.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.zhangmz.simpleframe.core.util.ReflectionUtil;

/**
 * 
 * @ClassName:BeanHelper 
 * @Description:Bean 助手类
 * @author:张孟志
 * @date:2015年12月13日 下午6:30:06 
 * @version V1.0
 * 说明：ClassHelper 可以获取所加载的类，但无法通过类来实例化对象。
 *     将使用BeanHelper来操作工具类ReflectionUtil，反射方式实例化对象。
 *     BeanHelper 相当于一个“Bean”容器。
 */
public final class BeanHelper {

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    /**
     * 获取 Bean 映射
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取 Bean 实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置 Bean 实例
     */
    public static void setBean(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }
}
