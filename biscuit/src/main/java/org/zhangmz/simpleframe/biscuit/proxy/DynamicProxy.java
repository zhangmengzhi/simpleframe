package org.zhangmz.simpleframe.biscuit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @ClassName:DynamicProxy 
 * @Description:JDK动态代理
 * @author:张孟志
 * @date:2015年12月16日 下午3:26:38 
 * @version V1.0
 * 说明：动态代理演示
 */
public class DynamicProxy implements InvocationHandler {

	private Object target;
	
	public DynamicProxy(Object target) {
		this.target = target;
	}
	
	/**
	 * 
	 * @Title: getProxy 
	 * @Description: 获取代理
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月16日 下午3:37:27
	 * 说明：为更方便使用代理，避免到处都是Proxy.newProxyInstance方法
	 *      将Object强转为T，是向下转型，@SuppressWarnings("unchecked")屏蔽警告
	 */
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(this.target.getClass().getClassLoader(), 
											this.target.getClass().getInterfaces(), 
											this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(target, args);
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
	 * 增加日期:2015年12月16日 下午3:40:40
	 * 说明：演示动态代理的使用
	 */
	public static void main(String[] args) {
		DynamicProxy dynamicProxy = new DynamicProxy(new SubjectImpl());
		Subject subjectProxy = dynamicProxy.getProxy();
		subjectProxy.say("zhangmengzhi");
	}

}
