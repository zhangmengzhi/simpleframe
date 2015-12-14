package org.zhangmz.simpleframe.core.bean;

import java.lang.reflect.Method;

/**
 * 
 * @ClassName:Handler 
 * @Description:请求处理对象
 * @author:张孟志
 * @date:2015年12月14日 下午7:48:44 
 * @version V1.0
 * 说明：封装 Action 信息
 */
public class Handler {

    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
