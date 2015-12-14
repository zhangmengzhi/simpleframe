package org.zhangmz.simpleframe.core;

import org.zhangmz.simpleframe.core.helper.*;
import org.zhangmz.simpleframe.core.util.ClassUtil;

/**
 * 
 * @ClassName:HelperLoader 
 * @Description:集中加载类
 * @author:张孟志
 * @date:2015年12月14日 下午8:07:03 
 * @version V1.0
 * 说明：加载相应的 Helper 类
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanHelper.class,
            // AopHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}