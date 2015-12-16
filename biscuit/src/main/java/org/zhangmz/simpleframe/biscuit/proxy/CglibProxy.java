package org.zhangmz.simpleframe.biscuit.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @ClassName:CglibProxy 
 * @Description:CGlib动态代理
 * @author:张孟志
 * @date:2015年12月16日 下午3:43:52 
 * @version V1.0
 * 说明：CGlib动态代理演示
 */
public class CglibProxy implements MethodInterceptor {

	/**
	 * 使用单例模式
	 */
	private static CglibProxy instance = new CglibProxy();
	
	private CglibProxy() {}
	
	public static CglibProxy getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls) {
		return (T) Enhancer.create(cls, this);
	}
	
	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object result = proxy.invokeSuper(object, args);
		after();
		return result;
	}

	private void before() {
		System.out.println("BEFORE");
	}

	private void after() {
		System.out.println("AFTER");
	}

	/**
	 * 
	 * @Title: main 
	 * @Description: 
	 * @param args
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月16日 下午3:53:41
	 * 说明：演示CGlib动态代理的使用
	 */
	public static void main(String[] args) {
		Subject subjectProxy = CglibProxy.getInstance().getProxy(SubjectImpl.class);
		subjectProxy.say("zhangmengzhi");
	}

}
