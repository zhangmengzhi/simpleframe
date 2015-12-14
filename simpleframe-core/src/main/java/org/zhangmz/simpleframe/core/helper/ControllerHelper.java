package org.zhangmz.simpleframe.core.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.zhangmz.simpleframe.core.annotation.Action;
import org.zhangmz.simpleframe.core.bean.Handler;
import org.zhangmz.simpleframe.core.bean.Request;
import org.zhangmz.simpleframe.core.util.ArrayUtil;
import org.zhangmz.simpleframe.core.util.CollectionUtil;

/**
 * 
 * @ClassName:ControllerHelper 
 * @Description:控制器助手类
 * @author:张孟志
 * @date:2015年12月14日 下午7:53:22 
 * @version V1.0
 * 说明：通过ClassHelper，获取所有定义了Controller注解的类，
 *      可以通过反射获取该类中所有带有Action注解的方法，获取
 *      Action注解中的请求表达式，进而获取请求方法与请求路径。
 *      封装一个请求对象Request与处理对象Handler，最后将Request
 *      与Handler建立一个映射关系，放入一个ActionMap中，并提供
 *      一个可根据请求方法与请求路径获取对象的方法（也可根据Request获取）。
 */
public final class ControllerHelper {

	/**
	 * 用于存放请求与处理器的映射关系
	 */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                        	// 注意Action注解的参数以“:”分割
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取 Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return getHandler(request);
    }
    
    /**
     * 获取 Handler
     */
    public static Handler getHandler(Request request) {
        return ACTION_MAP.get(request);
    }
}
