package org.zhangmz.simpleframe.biscuit.proxy;

import org.zhangmz.simpleframe.biscuit.proxy.Subject;

/**
 * 
 * @ClassName:StaticProxy 
 * @Description:静态代理
 * @author:张孟志
 * @date:2015年12月16日 下午3:06:29 
 * @version V1.0
 * 说明：静态代理类
 */
public class StaticProxy implements Subject {

	private Subject subject;
	
	public StaticProxy() {
		this.subject = new SubjectImpl();
	}
	
	public StaticProxy(Subject subject) {
		this.subject = subject;
	}
	
	@Override
	public void say(String name) {
		before();
		this.subject.say(name);
		after();
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
	 * 增加日期:2015年12月16日 下午3:10:18
	 * 说明：演示静态代理的使用
	 */
	public static void main(String[] args) {
		// StaticProxy subjectProxy = new StaticProxy();
		StaticProxy subjectProxy = new StaticProxy(new SubjectImpl());
		subjectProxy.say("zhangmengzhi");
	}
}
