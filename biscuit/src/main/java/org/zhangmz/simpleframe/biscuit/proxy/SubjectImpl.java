package org.zhangmz.simpleframe.biscuit.proxy;

/**
 * 
 * @ClassName:SubjectImpl 
 * @Description:实现类
 * @author:张孟志
 * @date:2015年12月16日 下午3:01:38 
 * @version V1.0
 * 说明：实现了代理接口。
 */
public class SubjectImpl implements Subject {

	@Override
	public void say(String name) {
		System.out.println("Hello, " + name);
	}

}
