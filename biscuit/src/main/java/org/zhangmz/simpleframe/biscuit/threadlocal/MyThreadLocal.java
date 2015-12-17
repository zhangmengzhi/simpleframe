package org.zhangmz.simpleframe.biscuit.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName:MyThreadLocal 
 * @Description:自己实现的ThreadLocal
 * @author:张孟志
 * @date:2015年12月17日 下午4:25:18 
 * @version V1.0
 * 说明：
 */
public class MyThreadLocal<T> {

	private Map<Thread, T> container = Collections.synchronizedMap(
											new HashMap<Thread, T>());
	
	public void set(T value) {
		container.put(Thread.currentThread(), value);
	}
	
	public T get() {
		Thread thread = Thread.currentThread();
		T value = container.get(thread);
		if(null == value && !container.containsKey(thread)) {
			value = initialValue();
			container.put(thread, value);
		}
		return value;
	}
	
	public void remove() {
		container.remove(Thread.currentThread());
	}
	
	protected T initialValue() {
		return null;
	}
}
