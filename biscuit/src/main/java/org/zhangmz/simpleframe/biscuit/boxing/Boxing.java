package org.zhangmz.simpleframe.biscuit.boxing;

/**
 * 
 * @ClassName:Boxing 
 * @Description:自动封箱/拆箱 
 * @author:张孟志
 * @date:2015年12月24日 上午10:09:47 
 * @version V1.0
 * 说明：演示自动封箱/拆箱 
 */
public class Boxing {

	public static void main(String[] args) {
		// 1、自动封箱
		Integer number = 100;  
		// Oracle/Sun的JDK上，编译程序会展开为
		// Integer localInteger = Integer.valueOf(100);

		// 2、自动拆箱
		int numberInt = number;
		// Oracle/Sun的JDK上，编译程序会展开为
		// int numberInt = number.intValue();
		
		// 3、Integer.valueOf()内部操作
		// 默认IntegerCache.low=-128  IntegerCache.high=127
		// 启动JVM时，可以指定：
		// java -Djava.lang.Integer.IntegerCache.high=300 org.zhangmz.simpleframe.biscuit.boxing.Boxing
		Integer i1 = 100;
		Integer i2 = 100;  // -128~127，会从缓存中传回Integer实例
		
		if(i1 == i2) {
			// 会执行这一句
			System.out.println("i1 == i2");
		} else {
			System.out.println("i1 != i2");
		}
		
		Integer i3 = 200;
		Integer i4 = 200;  // 不在-128~127，直接创建Integer实例
		
		if(i3 == i4) {
			System.out.println("i3 == i4");
		} else {
			// 会执行这一句
			System.out.println("i3 != i4");
		}
		
		Integer i5 = 200;
		Integer i6 = 200;  // 不在-128~127，直接创建Integer实例
		
		if(i5.equals(i6)) {
			// 会执行这一句
			System.out.println("i5.equals(i6)");
		} else {
			// 会执行这一句
			System.out.println("!i5.equals(i6)");
		}
	}

}
